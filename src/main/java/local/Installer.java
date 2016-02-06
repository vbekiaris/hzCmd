package local;

import global.Bash;

import java.io.IOException;
import java.util.List;

public abstract class Installer {

    public static final String REMOTE_HZCMD_ROOT = "hzCmd-root";
    public static final String REMOTE_HZCMD_ROOT_LIB = REMOTE_HZCMD_ROOT+"/" + "lib";

    public static final String REMOTE_HZCMD_ROOT_FULL_PATH ="$HOME/"+REMOTE_HZCMD_ROOT;
    public static final String REMOTE_HZCMD_LIB_FULL_PATH = "$HOME/"+ REMOTE_HZCMD_ROOT_LIB;

    private static final String HOME = "HOME";
    private static final String M2_DIR = "/.m2/";
    private static final String M2_Repo = System.getenv(HOME)+M2_DIR;

    public static void install(BoxManager boxes, JvmFactory jvmFactory, boolean ee,  String... versions) throws IOException, InterruptedException {

        String mainJars = Bash.find(M2_Repo, "hzCmd-1.0.1.jar");
        String cacheJars = Bash.find(M2_Repo, "cache-api-1.0.0.jar");
        String guavaars = Bash.find(M2_Repo, "guava-15.0-rc1.jar");
        String hdr = Bash.find(M2_Repo, "HdrHistogram-2.1.8.jar");
        String mq = Bash.find(M2_Repo, "activemq-all-5.13.0.jar");
        String metrics = Bash.find(M2_Repo, "metrics-core-3.1.1.jar");
        String slf4j = Bash.find(M2_Repo, "slf4j-api-1.7.7.jar");
        String chronicle = Bash.find(M2_Repo, "chronicle-3.6.0.jar");
        String lang = Bash.find(M2_Repo, "lang-6.7.6.jar");



        boxes.mkdir(REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(mainJars, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(cacheJars, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(guavaars, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(hdr, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(mq, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(metrics, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(slf4j, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(chronicle, REMOTE_HZCMD_ROOT_LIB);
        boxes.upload(lang, REMOTE_HZCMD_ROOT_LIB);

        for (String version : versions) {

            String uploadDir = jvmFactory.getVendorLibDir(version);
            boxes.mkdir(uploadDir);

            List<String> names = jvmFactory.getVendorLibNames(version, ee);
            for (String name : names) {
                String jar = Bash.find(M2_Repo, name);
                boxes.upload(jar,  uploadDir);
            }
        }
    }

}