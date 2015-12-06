package local;

import global.Bash;

import java.io.IOException;

public abstract class Installer {

    public static String REMOTE_ROOT = "hzCmd";
    public static String REMOTE_LIB = REMOTE_ROOT + "/lib";
    public static String REMOTE_HZ_LIB = REMOTE_ROOT + "/hzType-lib";

    public static String HOME = "HOME";
    public static String M2_DIR = "/.m2/";
    public static String M2_Repo = System.getenv(HOME)+M2_DIR;

    public static String hazelcast = "hazelcast-";
    public static String hazelcastEE = hazelcast+"enterprise-";

    public static String hazelcastClient = hazelcast+"client-";
    public static String hazelcastClientEE = hazelcastEE+"client-";

    public static String jar = ".jar";


    public static void install(RemoteBoxManager boxes, boolean ee,  String... versions) throws IOException, InterruptedException {

        System.out.println("install on "+boxes.count()+" boxes");
        String memberJar;
        String clientJar;

        String mainJars = Bash.find(M2_Repo, "hazellite-1.0-SNAPSHOT.jar");
        String cacheJars = Bash.find(M2_Repo, "cache-api-1.0.0.jar");
        String guavaars = Bash.find(M2_Repo, "guava-15.0-rc1.jar");

        boxes.sshCmd("mkdir -p "+REMOTE_LIB);
        boxes.upload(mainJars, REMOTE_LIB);
        boxes.upload(cacheJars, REMOTE_LIB);
        boxes.upload(guavaars, REMOTE_LIB);

        for (String version : versions) {
            if (ee) {
                memberJar = Bash.find(M2_Repo, hazelcastEE + version + jar);
                clientJar = Bash.find(M2_Repo, hazelcastClientEE + version + jar);
            } else {
                memberJar = Bash.find(M2_Repo, hazelcast + version + jar);
                clientJar = Bash.find(M2_Repo, hazelcastClient + version + jar);
            }
            boxes.sshCmd("mkdir -p " + REMOTE_HZ_LIB+"/"+version);
            boxes.upload(memberJar,  REMOTE_HZ_LIB+"/"+version);
            boxes.upload(clientJar,  REMOTE_HZ_LIB+"/"+version);
        }
    }

    public static void uninstall(RemoteBoxManager boxes) throws IOException, InterruptedException {
        boxes.sshCmd("rm -fr "+REMOTE_ROOT);
    }
}