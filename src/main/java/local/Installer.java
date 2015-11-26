package local;

import global.Args;
import global.Bash;

import java.io.IOException;

public abstract class Installer {

    public static String REMOTE_ROOT = "hzCluster";
    public static String HOME = "HOME";
    public static String M2_DIR = "/.m2/";
    public static String M2_Repo = System.getenv(HOME)+M2_DIR;

    public static boolean ee = false;

    public static String hazelcast = "hazelcast-";
    public static String hazelcastEE = hazelcast+"enterprise-";

    public static String hazelcastClient = hazelcast+"client-";
    public static String hazelcastClientEE = hazelcastEE+"client-";

    public static String version = "???";
    public static String jar = ".jar";


    public static void install(RemoteBoxes boxes) throws IOException, InterruptedException {

        String memberJar;
        String clientJar;
        if(ee){
            memberJar = Bash.find(M2_Repo, hazelcastEE + version + jar);
            clientJar = Bash.find(M2_Repo, hazelcastClientEE + version + jar);
        }else {
            memberJar = Bash.find(M2_Repo, hazelcast + version + jar);
            clientJar = Bash.find(M2_Repo, hazelcastClient + version + jar);
        }
        String mainJars = Bash.find(M2_Repo, "hazellite-1.0-SNAPSHOT.jar");

        boxes.sshCmd("mkdir -p "+REMOTE_ROOT+"/lib");
        boxes.upload(memberJar, REMOTE_ROOT+"/lib/");
        boxes.upload(clientJar, REMOTE_ROOT+"/lib/");
        boxes.upload(mainJars, REMOTE_ROOT+"/lib/");
    }

    public static void uninstall(RemoteBoxes boxes) throws IOException, InterruptedException {
        boxes.sshCmd("rm -fr "+REMOTE_ROOT);
    }
}