package local;

import global.Bash;
import global.NodeType;
import remote.bench.BenchType;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

import static global.Utils.rangeMap;

public class ClusterManager implements Serializable {

    private final String clusterId;

    private String[] versions;

    private BoxManager boxes;
    private Map<String, RemoteJvm> jvms = new HashMap();

    private int membersOnlyCount;
    private int memberCount=0;
    private int clientCount=0;
    private String brokerIP;

    private JvmFactory jvmFactory;

    public ClusterManager(String clusterId, BoxManager boxes, String brokerIP, JvmFactory jvmFactory) throws Exception {
        this.clusterId =clusterId;
        this.boxes=boxes;
        this.brokerIP =brokerIP;
        this.jvmFactory=jvmFactory;
        jvmFactory.clusterInit(boxes);
    }

    public boolean matchClusterId(String clusterId){
        return this.clusterId.matches(clusterId);
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

    public void setVersion(String[] versions) {
      this.versions=versions;
    }

    public String getVersionString( ) {
        return Arrays.toString(versions).replace("[", "").replace("]", "");
    }


    public List<RemoteJvm> addMembers(int qty, String version, String options, String cwdFiles) throws Exception {
        return addJvms(qty, version, options, cwdFiles, NodeType.Member);
    }

    public List<RemoteJvm> addClients(int qty, String version, String options, String cwdFiles) throws Exception {
        return addJvms(qty, version, options, cwdFiles, NodeType.Client);
    }

    private List<RemoteJvm> addJvms(int qty, String version, String options, String cwdFiles, NodeType type) throws Exception {
        List<RemoteJvm> added = new ArrayList();
        for(int i=0; i<qty; i++) {
            added.add(addJvm(version, options, cwdFiles, type));
        }
        return added;
    }

    private RemoteJvm addJvm(String jarVersion, String options, String cwdFiles, NodeType type) throws Exception {
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

        jvm.startJvm(options, jvmFactory.getVendorLibDir(jarVersion), this, brokerIP);
        return jvm;
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


    public void invokeAsync(String jvmId, int threadCount, String method, String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.invokeAsync(threadCount, method, taskId);
        }
    }

    public void invokeSync(String jvmId, int threadCount, String method, String taskId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.invokeSync(threadCount, method, taskId);
        }
        getResponse(jvmId);
    }

    public void getResponse(String jvmId) throws IOException, InterruptedException, JMSException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            Object o = jvm.getResponse();
            printResponse(o);
        }
    }

    private void printResponse(Object o){
        if(o instanceof Exception){
            Exception e = (Exception) o;
            System.out.println(Bash.ANSI_RED+" "+e+" "+e.getCause()+Bash.ANSI_RESET);
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

    public void stop(String jvmId, String taskId) throws IOException, InterruptedException {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            //jvm.stop();
        }
    }

    public void restart(String jvmId) throws Exception {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.reStartJvm(this);
        }
    }

    public void bounce(String jvmId, int delaySec) throws Exception {
        kill((jvmId));
        Thread.sleep(delaySec*1000);
        restart(jvmId);
    }

    public void restart(String jvmId, String version, String options) throws Exception {
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            if (version==null && options==null ){
                jvm.reStartJvm(this);
            }else{
                jvm.startJvm(version, options, this, brokerIP);
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
            error = error || jvm.printErrors();
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
        for(RemoteJvm jvm : getMatchingJms(jvmId)){
            jvm.downlonad(destDir+"/"+clusterId);
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