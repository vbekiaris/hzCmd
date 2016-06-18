package local;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import global.Bash;
import global.NodeType;
import mq.MQ;
import remote.Controler;
import remote.bench.Bench;
import remote.bench.BenchType;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static global.Utils.rangeMap;

public class ClusterManager implements Serializable {

    private final String clusterId;
    private List<String> versions = new ArrayList<String>();

    private BoxManager boxes = new BoxManager();
    private Map<String, RemoteJvm> jvms = new HashMap();

    private Multimap<Box, String> lauchMap;
    private int membersOnlyCount;
    private int memberCount=0;
    private int clientCount=0;
    private String brokerIP;

    private JvmFactory jvmFactory;

    public ClusterManager(String clusterId, String brokerIP, JvmFactory jvmFactory) throws Exception {
        this.clusterId =clusterId;
        this.brokerIP =brokerIP;
        this.jvmFactory=jvmFactory;
    }

    public String getClusterId() {
        return clusterId;
    }

    public BoxManager getBoxManager( ){
        return boxes;
    }


    public void setMembersOnlyCount(int count) {
        if(count < 0 || count >= boxes.size()){
            membersOnlyCount=0;
        }else{
            membersOnlyCount = count;
        }
    }


    public String getLastVersion() {
        if(versions==null || versions.size()==0){
            return null;
        }

        return versions.get(versions.size()-1);
    }


    public void addVersion(String version){
        if ( version!=null && !versions.contains(version) ){
            versions.add(version);
        }
    }

    public boolean containsVersion(String version){
        return versions.contains(version);
    }

    public void addVersions(String[] versions){
        if(versions!=null){
            for (String version : versions) {
                addVersion(version);
            }
        }
    }

    public void addMembers(int qty, String version, String options, String cwdFiles) throws Exception {
         addJvms(qty, version, options, cwdFiles, NodeType.Member);
    }

    public void addClients(int qty, String version, String options, String cwdFiles) throws Exception {
         addJvms(qty, version, options, cwdFiles, NodeType.Client);
    }

    private void addJvms(int qty, String version, String options, String cwdFiles, NodeType type) throws Exception {

        if(qty==0){
            return;
        }

        addVersion(version);

        lauchMap = ArrayListMultimap.create();
        for (int i = 0; i < qty; i++) {
            addJvm(version, options, cwdFiles, type);
        }

        List<String> files = jvmFactory.stuffToUpload(this);

        ExecutorService executor = Executors.newFixedThreadPool(lauchMap.keySet().size());
        for (Box box : lauchMap.keySet()) {
            executor.submit( new Runner( box, files ) );
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public String getVersionsString() {
        return versions.toString();
    }

    public void addUniquBoxes(BoxManager bm) {
        boxes.addBoxes(bm);
    }

    private  class Runner implements Callable<Object> {

        private Box box;
        private List<String> files;

        public Runner(Box box, List<String> files){
            this.box=box;
            this.files=files;
        }

        public Object call() throws IOException, InterruptedException, JMSException {

            for (String file : files) {
                box.scpUp(file, ".");
            }

            File launchFile = new File(box.pub + "launch.sh");
            FileOutputStream fos = new FileOutputStream(launchFile);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (String s : lauchMap.get(box)) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();

            Bash.chmodExe(launchFile.getName());
            box.scpUp(launchFile.getName(), ".");
            Bash.rm(launchFile.getName());

            String pids = box.ssh("./" + launchFile.getName());

            String delim = " \n";
            StringTokenizer st = new StringTokenizer(pids,delim);

            List<RemoteJvm> started = new ArrayList<RemoteJvm>();
            while (st.hasMoreTokens()) {
                String pid = st.nextToken();
                String jmvId = st.nextToken();

                RemoteJvm jvm = jvms.get(jmvId);
                jvm.setPid(pid);

                started.add(jvm);
            }


            while(!started.isEmpty()){
                ListIterator<RemoteJvm> iter = started.listIterator();

                while(iter.hasNext()){
                    Object o;
                    if(  (o = iter.next().getResponse(8)) != null){
                        printResponse(o);
                        iter.remove();
                    }

                    //System.out.println("remaining " + started.size());

                }
            }

            return null;
        }
    }



    private void addJvm(String jarVersion, String options, String cwdFiles, NodeType type) throws Exception {
        int idx;
        int count;
        if(type == NodeType.Member) {
            idx = rangeMap(memberCount++, 0, boxes.size() - membersOnlyCount);
            count=memberCount;
        }else {
            idx = rangeMap(clientCount++, membersOnlyCount, boxes.size());
            count=clientCount;
        }
        RemoteJvm jvm = jvmFactory.createJvm(boxes.get(idx), type, count, clusterId);
        jvm.uploadcwd(cwdFiles);
        jvms.put(jvm.getId(), jvm);

        String lauchStr = jvm.startJvm(options, jvmFactory.getVendorLibDir(jarVersion), this, brokerIP);

        lauchMap.put(boxes.get(idx), lauchStr);

    }

    public int getMemberCount( ) {
        int count=0;
        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.isMember() ){
                count++;
            }
        }
        return count;
    }

    public int getClientCount( ) {
        int count=0;
        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.isClient() ){
                count++;
            }
        }
        return count;
    }

    public List<RemoteJvm> getMemberBoxes( ) {
        List<RemoteJvm> matching = new ArrayList<RemoteJvm>();

        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.isMember() ){
                matching.add(jvm);
            }
        }
        return matching;
    }

    public List<RemoteJvm> getMatchingJms(String jvmId) {
        List<RemoteJvm> matching = new ArrayList<RemoteJvm>();

        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.getId().matches(".*" + jvmId + ".*") ){
                matching.add(jvm);
            }
        }
        return matching;
    }

    public void restartEmbeddedObject(String jvmId) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.restartEmbeddedObject();
        }
        getResponse(jvmId);
    }

    public void load(String jvmId, String taskId, String className) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.load(taskId, className);
        }
        getResponse(jvmId);
    }

    public void setThreadCount(String jvmId, String taskId, int threadCount) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.setThreadCount(taskId, threadCount);
        }
        getResponse(jvmId);
    }


    public void setBenchType(String jvmId, String taskId, BenchType type, long intervalNanos, boolean allowException, String outFile) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.setBenchType(taskId, type, intervalNanos, allowException, outFile);
        }
        getResponse(jvmId);
    }


    public void writeMetaDataCmd(String jvmId, String taskId, String metaData) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.writeMetaDataCmd(taskId, metaData);
        }
        getResponse(jvmId);
    }

    public void initBench(String jvmId,  String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.initBench(taskId);
        }
        getResponse(jvmId);
    }

    public void warmupBench(String jvmId,  String taskId, int seconds) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.warmupBench(taskId, seconds);
        }
        getResponse(jvmId);
    }

    public void runBench(String jvmId,  String taskId, int seconds) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.runBench(taskId, seconds);
        }
        getResponse(jvmId);
    }

    public void cleanupBench(String jvmId,  String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.cleanupBench(taskId);
        }
        getResponse(jvmId);
    }

    public void setField(String jvmId, String taskId, String field, String value) throws Exception {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.setField(taskId, field, value);
        }

        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            Object o = jvm.getResponse();
            printResponse(o);
        }
    }


    public void getResponse(String jvmId) throws IOException, InterruptedException, JMSException {

        List<RemoteJvm> matchingJms = getMatchingJms(jvmId);

        long start = System.currentTimeMillis();
        int count = matchingJms.size();
        System.out.println("start get response for "+count);


        //int prevRemaining=0;
        while(!matchingJms.isEmpty()){
            ListIterator<RemoteJvm> iter = matchingJms.listIterator();
            while(iter.hasNext()){
                Object o;
                if(  (o = iter.next().getResponse(10)) != null){
                    printResponse(o);
                    iter.remove();
                }
            }
            //if(matchingJms.size()!=prevRemaining) {
            //    System.out.println("remaining " + matchingJms.size());
            //    prevRemaining=matchingJms.size();
            //}
        }

        long end = System.currentTimeMillis();
        System.out.println("response for "+count+" seconds "+(end-start)/1000);
    }

    private void printResponse(Object o){
        if(o instanceof Exception){
            Exception e = (Exception) o;
            System.out.println(Bash.ANSI_RED + e +e.getCause()+Bash.ANSI_RESET);
            e.printStackTrace();

        }else{
            System.out.println(Bash.ANSI_GREEN + o + Bash.ANSI_RESET);
        }
    }

    public void getResponses(String jvmId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            printResponse(jvm.getResponse());
        }
    }


    public void restart(String jvmId) throws Exception {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.reStartJvm(this);
        }
    }


    public void restart(String jvmId, String version, boolean ee) throws Exception {
        if(version!=null && !containsVersion(version)){
            Installer.installVendorLib(boxes, jvmFactory, ee, version);
        }
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            if (version==null ){
                jvm.reStartJvm(this);
            }else{
                jvm.reStartJvm(jvmFactory.getVendorLibDir(version), this, brokerIP);
            }
        }
    }

    public void clean(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.clean();
        }
    }

    public void exit(String jvmId) throws JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.exit();
        }
    }

    public void kill(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.kill();
        }
    }

    public void printJvmInfo(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm);
        }
    }

    public boolean printErrors(String jvmId) throws IOException, InterruptedException {
        boolean error=false;
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            error |= jvm.printErrors();
        }
        return error;
    }

    public void ls(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.ls());
        }
    }

    public void cat(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.cat());
        }
    }

    public void jstack(String jvmId, String file) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.jstack(file);
        }
    }


    public void tail(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm);
            jvm.tail();
        }
    }

    public void grep(String jvmId, String args) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.grep(args));
        }
    }

    public void ssh(String jvmId, String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            System.out.println(jvm.ssh(cmd));
        }
    }

    public void uploadLib(String src) throws IOException, InterruptedException {
        boxes.upload(src, Installer.REMOTE_HZCMD_ROOT_LIB);
    }

    public void downlonad(String jvmId, String destDir) throws IOException, InterruptedException {

        for (Box box : boxes.getBoxList()) {
            box.downlonad(Installer.REMOTE_HZCMD_ROOT+"/*", destDir+"/"+clusterId);
        }
    }

    private String toString_memberJvms(){
        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            if(jvm.isMember()){
                jvms+=jvm+"\n";
            }
        }
        return jvms;
    }

    private String toString_clientJvms(){
        String jvms = new String();
        for(RemoteJvm jvm : this.jvms.values()){
            if(jvm.isClient()){
                jvms+=jvm+"\n";
            }
        }
        return jvms;
    }



    @Override
    public String toString() {

        String jvms = toString_memberJvms();
        jvms += toString_clientJvms();

        return Bash.ANSI_YELLOW+"ClusterManager" +
                " clusterId=" + clusterId +
                ", membersOnlyCount=" + membersOnlyCount +
                ", memberCount=" + memberCount +
                ", clientCount=" + clientCount +
                ", boxCount=" + boxes.size() +
                ", versions=" + versions +
                ", " + boxes +
                "" + jvms +
                Bash.ANSI_RESET;
    }

    public JvmFactory getJvmFactory() {
        return jvmFactory;
    }

    public void drainQ() throws JMSException {
        for(RemoteJvm jvm : jvms.values()){
            jvm.drainQ();
        }
    }
}