package main;

import cmdline.AddClient;
import cmdline.AddMember;
import cmdline.CmdLine;
import cmdline.Command;
import global.Args;
import global.Bash;
import hz.HzJvmFactory;
import jms.MQ;
import local.BoxManager;
import local.ClusterManager;
import local.Installer;
import local.RemoteJvm;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;

// mvn clean install dependency:copy-dependencies
//TODO WAN REP xml SETUP
//TODO man center integ
//TODO exampe 3 3node cluster wan replicate ring, with rolling upgrade,  members putting,  clients getting,  kill restart members,  man center isRunning,
public class HzCmd implements Serializable {

    private Map<String, BoxManager> boxes = new HashMap();
    private Map<String, ClusterManager> clusters = new HashMap();

    public void showSSH(boolean show){
        Bash.showSSH = show;
    }

    private String homeIp;
    public void setHomeIp(String homeIp){
        this.homeIp=homeIp;
    }

    public void listen() throws IOException, InterruptedException{
        String eventQ = System.getProperty("user.dir")+"/"+Args.EVENTQ.name();
        while (true){
            try {
                Object o = MQ.receiveObj(eventQ);
                if (o instanceof Exception){
                    Exception e = (Exception) o;
                    System.out.println(Bash.ANSI_RED+" "+e+" "+e.getCause()+Bash.ANSI_RESET);
                    e.printStackTrace();
                }else{
                    System.out.println(o);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBoxes(String user, String file) throws IOException, InterruptedException{
        if (boxes.containsKey(file)){
            System.out.println(Bash.ANSI_RED+"file "+file + " all ready imported"+Bash.ANSI_RESET);
            return;
        }

        BoxManager b = new BoxManager(file);
        b.addBoxes(user, file);
        boxes.put(file, b);
    }

    public void addCluster(String clusterId, String boxGroupId) throws Exception{
        if (clusters.containsKey(clusterId)){
            System.out.println(Bash.ANSI_RED+"cluster "+clusterId + " all ready created"+Bash.ANSI_RESET);
            return;
        }

        BoxManager boxi = boxes.get(boxGroupId);
        if(boxi == null){
            System.out.println(Bash.ANSI_RED+"box group "+boxGroupId + " not found"+Bash.ANSI_RESET);
            return;
        }

        ClusterManager cluster = new ClusterManager(clusterId, boxi, homeIp, new HzJvmFactory());
        clusters.put(cluster.getClusterId(), cluster);
        System.out.println(cluster);
    }

    public void install(String clusterId, boolean ee, String... versions) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                Installer.install(c.getBoxManager(), ee, versions);
            }
        }
    }

    public void dedicatedMembers(String clusterId, int memberBox) {
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(clusterId)){
                c.setMembersOnlyCount(memberBox);
            }
        }
    }

    public void addMembers(AddMember cmd) throws Exception {
        List<RemoteJvm> added = new ArrayList();
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(cmd.cluster)){
                added.addAll( c.addMembers(cmd.qty, cmd.version, cmd.jvmOptions) );
            }
        }
        checkAddJvms(added);
    }

    public void addClients(AddClient cmd) throws Exception {
        List<RemoteJvm> added = new ArrayList();
        for (ClusterManager c : clusters.values()) {
            if(c.matchClusterId(cmd.cluster)){
                added.addAll( c.addClients(cmd.qty, cmd.version, cmd.jvmOptions) );
            }
        }
        checkAddJvms(added);
    }

    private void checkAddJvms(List<RemoteJvm> added) throws JMSException {
        for (RemoteJvm jvm : added) {
            System.out.println(jvm);
        }
        for (RemoteJvm jvm : added) {
            Object o = jvm.jvmStartResponse();
            if(o instanceof Exception){
                System.out.println(Bash.ANSI_RED+o+Bash.ANSI_RESET);
            }else{
                System.out.println(Bash.ANSI_GREEN+o+Bash.ANSI_RESET);
            }
        }
    }

    public void exit(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.exit(jvmId);
        }
    }

    public void kill(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.kill(jvmId);
        }
    }

    public void restart(String jvmId, String version,  String options) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.restart(jvmId, version, options);
        }
    }

    public void cat(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.cat(jvmId);
        }
    }

    public void tail(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.tail(jvmId);
        }
    }

    public void grep(String jvmId, String grepArgs) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.grep(jvmId, grepArgs);
        }
    }

    public void download(String jvmId, String dir) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.downlonad(jvmId, dir);
        }
    }

    public void clean(String jvmId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.clean(jvmId);
        }
    }

    public void ssh(String jvmId, String cmd) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.ssh(jvmId, cmd);
        }
    }


    public void wipe( ) throws IOException, InterruptedException {
        for (ClusterManager c : clusters.values()) {
            c.kill(".*");
        }
        clusters.clear();

        for (BoxManager boxManager : boxes.values()) {
            boxManager.rm(Installer.REMOTE_HZCMD_ROOT);
        }
        boxes.clear();
    }

    public void load(String jvmId,  String taskId, String className) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.load(jvmId, taskId, className);
        }
    }

    public void setField(String jvmId, String taskId, String field, String value) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.setField(jvmId, taskId, field, value);
        }
    }


    public void invokeAsync(String jvmId, int threadCound, String method, String taksId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.invokeAsync(jvmId, threadCound, method, taksId);
        }
    }

    public void ping(String jvmId, long timeout) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.ping(jvmId);
        }
        for (ClusterManager c : clusters.values()) {
            c.getResponse(jvmId, timeout);
        }
    }


    public void invokeSync(String jvmId, int threadCound, String method, String taksId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.invokeSync(jvmId, threadCound, method, taksId);
        }
        for (ClusterManager c : clusters.values()) {
            c.getResponse(jvmId);
        }
    }

    public void invokeBenchMark(String jvmId, int threadCound, String taksId) throws Exception {
        invokeSync(jvmId, threadCound, "init", taksId);
        invokeSync(jvmId, threadCound, "warmup", taksId);
        invokeSync(jvmId, threadCound, "run", taksId);
    }

    public void stop(String jvmId, String taskId) throws Exception {
        for (ClusterManager c : clusters.values()) {
            c.stop(jvmId, taskId);
        }
    }

    @Override
    public String toString() {
        String clu="";
        for(ClusterManager c : clusters.values()) {
            clu += c.toString() + "\n";
        }
        return clu ;
    }

    private static HzCmd loadHzCmd(){
        HzCmd hzCmd = null;
        try {
            FileInputStream fileIn = new FileInputStream("HzCmd.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hzCmd = (HzCmd) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e) {
            hzCmd = new HzCmd();
        }
        return hzCmd;
    }

    private static void saveHzCmd(HzCmd hzCmd){
        try {
            FileOutputStream fileOut = new FileOutputStream("HzCmd.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hzCmd);
            out.close();
            fileOut.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        final HzCmd hzCmd = loadHzCmd();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {

                saveHzCmd(hzCmd);
                try {
                    MQ.shutdown();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        com.github.rvesse.airline.Cli<Runnable> parser = CmdLine.getParser();
        Runnable r = parser.parse(args);
        if (r instanceof Command){
            Command c = (Command)r;

            c.exe(hzCmd);

            try {
                MQ.shutdown();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else{
            r.run();
        }
    }
}