package local;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import global.*;
import local.bench.BenchManager;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static global.Utils.rangeMap;

public class ClusterContainer implements Serializable {

    private final String clusterId;
    private List<String> versions = new ArrayList<String>();

    private BoxManager boxes = new BoxManager();
    private Map<String, RemoteJvm> jvms = new HashMap();
    private volatile RemoteJvm ephemerialMember;

    private Multimap<Box, String> lauchMap;
    private int membersOnlyCount;
    private int memberCount=0;
    private int clientCount=0;
    private String brokerIP;

    private JvmFactory jvmFactory;

    public ClusterContainer(String clusterId, String brokerIP, JvmFactory jvmFactory) throws Exception {
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
        executor.awaitTermination(3, TimeUnit.HOURS);

    }

    public String getVersionsString() {
        return versions.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "-");
    }

    public void addUniquBoxes(BoxManager bm) {
        boxes.addBoxes(bm);
    }

    public int getBoxCount() {
        return boxes.getBoxCount();
    }



    private class Runner implements Callable<Object> {

        private Box box;
        private List<String> files;

        public Runner(Box box, List<String> files){
            this.box=box;
            this.files=files;
        }

        public List<RemoteJvm> call() throws Exception{

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

            for (RemoteJvm remoteJvm : started) {
                remoteJvm.startEmbeddedObject();
            }

            for (RemoteJvm remoteJvm : started) {
                Object response = remoteJvm.getResponse(Utils.TIMEOUT_5MIN);
                if(response==null){
                    System.out.println(Bash.ANSI_RED+"Timeout waiting for Jvm response"+Bash.ANSI_RESET);
                    System.exit(1);
                }
                printResponseExitOnError(response);
            }
            return started;
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


    public List<RemoteJvm> getMemberJvms( ) {
        List<RemoteJvm> matching = new ArrayList<RemoteJvm>();

        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.isMember() ){
                matching.add(jvm);
            }
        }
        return matching;
    }

    public void nominateRandomMemberJvm( ) {
        List<RemoteJvm> members = getMemberJvms();
        Random random = new Random();
        ephemerialMember = members.get(random.nextInt(members.size()));
    }

    public RemoteJvm getEphemerialMember(){
        return ephemerialMember;
    }

    public List<RemoteJvm> getClientJvms( ) {
        List<RemoteJvm> matching = new ArrayList<RemoteJvm>();

        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.isClient() ){
                matching.add(jvm);
            }
        }
        return matching;
    }

    public List<RemoteJvm> getMatchingJvms(String jvmId) {
        List<RemoteJvm> matching = new ArrayList<RemoteJvm>();

        for(RemoteJvm jvm : jvms.values()){
            if ( jvm.getId().matches(".*" + jvmId + ".*") ){
                matching.add(jvm);
            }
        }

        if(matching.size()==0){
            System.out.println(Bash.ANSI_RED+"Zero jvm's matching regex "+".*"+jvmId+".*" +Bash.ANSI_RESET);
            System.exit(1);
        }

        return matching;
    }

    public List<RemoteJvm> getMatchingMemberJvms(String jvmId) {
        List<RemoteJvm> all = getMatchingJvms(jvmId);

        ListIterator<RemoteJvm> iter = all.listIterator();
        while (iter.hasNext()) {
            if ( iter.next().getId().matches(".*Client.*") ){
                iter.remove();
            }
        }
        return all;
    }

    public List<RemoteJvm> getMatchingClientJvms(String jvmId) {
        List<RemoteJvm> all = getMatchingJvms(jvmId);

        ListIterator<RemoteJvm> iter = all.listIterator();
        while (iter.hasNext()) {
            if ( iter.next().getId().matches(".*Member.*") ){
                iter.remove();
            }
        }
        return all;
    }

    public void uploadToMemberCwd(String src) throws Exception{
        for (RemoteJvm remoteJvm : getMemberJvms()) {
            remoteJvm.uploadToCwd(src);
        }
    }

    public void uploadToMemberBoxUserRootDir(String src) throws Exception{
        for (RemoteJvm remoteJvm : getMemberJvms()) {
            remoteJvm.uploadToUserRootDir(src);
        }
    }


    public String getPublicMemberIps() {

        String ips = new String();
        for (RemoteJvm remoteJvm : getMemberJvms()) {
            ips += remoteJvm.getBox().pri+",";
        }
        return ips.substring(0,ips.length()-1);
    }

    public void restartEmbeddedObject(String jvmId) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.startEmbeddedObject();
        }
    }

    public void load(String jvmId, String taskId, String className) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.load(taskId, className);
        }
    }

    public void setThreadCount(String jvmId, String taskId, int threadCount) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.setThreadCount(taskId, threadCount);
        }
    }


    public void setBenchType(String jvmId, String taskId, BenchType type, long intervalNanos, boolean recordException, String outFile) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.setBenchType(taskId, type, intervalNanos, recordException, outFile);
        }
    }

    public void writeMetaDataCmd(String jvmId, String taskId, String metaData) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.writeMetaDataCmd(taskId, metaData);
        }
    }

    public void setField(String jvmId, String taskId, String field, String value) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.setField(taskId, field, value);
        }
    }

    public void initBench(String jvmId,  String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.initBench(taskId);
        }
    }

    public void warmupBench(String jvmId,  String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.warmupBench(taskId, seconds);
        }
    }

    public void runBench(String jvmId,  String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.runBench(taskId, seconds);
        }
    }

    public void submitBench(String jvmId,  String taskId, long seconds) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.submitBench(taskId, seconds);
        }
    }


    public void stopBench(String jvmId, String taskId) throws IOException, InterruptedException, JMSException{
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.stopBench(taskId);
        }
    }

    public void postPhaseBench(String jvmId, String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.postPhaseBench(taskId);
        }
    }

    public void removeBench(String jvmId, String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.removeBench(taskId);
        }
    }

    public void restart(String jvmId) throws Exception {
        List<RemoteJvm> members = getMatchingMemberJvms(jvmId);
        List<RemoteJvm> clients = getMatchingClientJvms(jvmId);

        restartJvmList(null, members);

        if(members.size()!=0 && clients.size()!=0){
            Thread.sleep(5000);
        }

        restartJvmList(null, clients);
    }

    public void restart(String jvmId, String version, boolean ee) throws Exception {
        if (version != null && !containsVersion(version)) {
            Installer.installVendorLib(boxes, jvmFactory, ee, version);
            addVersion(version);
        }

        List<RemoteJvm> members = getMatchingMemberJvms(jvmId);
        List<RemoteJvm> clients = getMatchingClientJvms(jvmId);

        restartJvmList(version, members);

        if(members.size()!=0 && clients.size()!=0){
            Thread.sleep(5000);
        }

        restartJvmList(version, clients);
    }

    private void restartJvmList(String version, List<RemoteJvm> jvms) throws Exception{
        for (RemoteJvm jvm : jvms) {
            if (version == null) {
                jvm.reStartJvm();
            } else {
                jvm.reStartJvm(jvmFactory.getVendorLibDir(version), this, brokerIP);
            }
        }
    }

    public void renice() throws Exception{
        for (RemoteJvm remoteJvm : getMemberJvms()) {
            remoteJvm.renice(-20);
        }
        for (RemoteJvm remoteJvm : getClientJvms()) {
            remoteJvm.renice(-19);
        }
    }

    public void getResponseExitOnException(String jvmId, BenchManager benchManager, long timeOutMillis) throws IOException, InterruptedException, JMSException {
        boolean exit=false;
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            Object o = jvm.getResponse(timeOutMillis);

            if(o==null){
                System.out.println(Bash.ANSI_RED+"Timeout! clusterId="+this.getClusterId()+" drivers="+jvmId+" timeoutSeconds="+TimeUnit.MILLISECONDS.toSeconds(timeOutMillis)+Bash.ANSI_RESET);

                if(benchManager!=null) {
                    System.out.println(Bash.ANSI_RED + benchManager.getFileName() + Bash.ANSI_RESET);
                    System.out.println(Bash.ANSI_RED + benchManager.currentBench_toString() + Bash.ANSI_RESET);
                }
                System.exit(1);
            }

            Gson gson = new Gson();
            ReplyMsg msg = gson.fromJson((String) o, ReplyMsg.class);
            System.out.println(msg);

            if(msg.error==true){
                exit=true;
            }
        }
        if(exit){
            System.out.println(Bash.ANSI_RED+"EXIT 1"+Bash.ANSI_RESET);
            System.exit(1);
        }
    }


    private void printResponseExitOnError(Object o){
        Gson gson = new Gson();
        ReplyMsg msg = gson.fromJson((String) o, ReplyMsg.class);
        System.out.println(msg);
        if(msg.error==true){
            System.out.println(Bash.ANSI_RED+"EXIT 1"+Bash.ANSI_RESET);
            System.exit(1);
        }
    }






    public void clean(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.clean();
        }
    }

    public void exit(String jvmId) throws JMSException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.exit();
        }
    }

    public void kill(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.kill();
        }
    }

    public void bashCmd(String jvmId, String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.bashCmd(cmd);
        }
    }

    public void freeze(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.freeze();
        }
    }

    public void unfreeze(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.unfreeze();
        }
    }

    public void printJvmInfo(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm);
        }
    }

    public void printJvmInfo(String msg, String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(msg+" "+jvm);
        }
    }

    public boolean printErrors(String jvmId) throws IOException, InterruptedException {

        boolean error=false;

        for (Box box : boxes.getBoxList()) {
            String err = box.findArgs(Installer.REMOTE_HZCMD_ROOT+"/*"+clusterId+"*", "-name exception.txt -o -name *.hprof -o -name *.oome -o -name hs_err_pid* -o -name *core*");

            if(err != null && !"".equals(err)){
                System.out.println(Bash.ANSI_RED+err+Bash.ANSI_RESET);
                error = true;
            }

        }
        return error;
    }

    public void ls(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.ls());
        }
    }

    public void ip(String jvmId) {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm.getBox().pub);
        }
    }


    public void cat(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.cat());
        }
    }

    public void jstack(String jvmId, String file) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            jvm.jstack(file);
        }
    }


    public void tail(String jvmId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm);
            jvm.tail();
        }
    }

    public void grep(String jvmId, String args) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm);
            System.out.println(jvm.grep(args));
        }
    }

    public void ssh(String jvmId, String cmd) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJvms(jvmId)){
            System.out.println(jvm.ssh(cmd));
        }
    }

    public void uploadLib(String src) throws IOException, InterruptedException {
        boxes.upload(src, Installer.REMOTE_HZCMD_ROOT_LIB);
    }

    public boolean downlonad(String destDir) throws IOException, InterruptedException {
        for (Box box : boxes.getBoxList()) {
            box.downlonad(Installer.REMOTE_HZCMD_ROOT+"/*"+clusterId+"*", destDir+"/"+clusterId);
            //box.downlonad("/tmp/hs_err_pid*", destDir+"/"+clusterId);
        }
        //Bash.executeCommandWithExitCode("gcViewerPics "+destDir+"/"+clusterId);
        return Bash.findLocalError(destDir+"/"+clusterId);
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

        return Bash.ANSI_YELLOW+"ClusterContainer" +
                " clusterId=" + clusterId +
                ", membersOnlyCount=" + membersOnlyCount +
                ", memberCount=" + memberCount +
                ", clientCount=" + clientCount +
                ", boxCount=" + boxes.size() +
                ", versions=" + versions +
                ", \n" + boxes +
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