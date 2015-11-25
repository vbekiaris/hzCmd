package local;

import global.Bash;

import java.io.IOException;

public abstract class Installer {

    public static String M2_HOME = "M2_HOME";
    public static String HOME = "HOME";

    public static boolean ee = false;

    public static String hazelcast = "hazelcast-";
    public static String hazelcastEE = hazelcast+"enterprise-";

    public static String hazelcastClient = hazelcast+"client-";
    public static String hazelcastClientEE = hazelcastEE+"client-";

    public static String version = "3.4";
    public static String jar = ".jar";

    public static void install(RemoteBoxes boxes) throws IOException, InterruptedException {
        String path = System.getenv(M2_HOME);

        if( path == null ){
            path = System.getenv(HOME)+"/.m2/";
        }
        String memberJar;
        String clientJar;
        if(ee){
            memberJar = Bash.find(path, hazelcastEE + version + jar);
            clientJar = Bash.find(path, hazelcastClientEE + version + jar);
        }else {
            memberJar = Bash.find(path, hazelcast + version + jar);
            clientJar = Bash.find(path, hazelcastClient + version + jar);
        }
        boxes.sshCmd("rm -fr lib");
        boxes.sshCmd("mkdir -p lib");
        boxes.upload(memberJar, "lib/");
        boxes.upload(clientJar, "lib/");
        boxes.upload("hazellite-1.0-SNAPSHOT.jar", "lib/");
    }
}