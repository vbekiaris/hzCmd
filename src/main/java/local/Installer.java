package local;

import global.Bash;

import java.io.IOException;
import java.util.List;

public abstract class Installer {

    public static final String REMOTE_HZCMD_ROOT = "hzCmd-root";
    private static final String LIB = REMOTE_HZCMD_ROOT+"/" + "lib";

    public static final String REMOTE_HZCMD_ROOT_FULL_PATH ="$HOME/"+REMOTE_HZCMD_ROOT;
    public static final String REMOTE_HZCMD_LIB_FULL_PATH = "$HOME/"+ LIB;

    private static final String HOME = "HOME";
    private static final String M2_DIR = "/.m2/";
    private static final String M2_Repo = System.getenv(HOME)+M2_DIR;

    /*
    public static String REMOTE_HZ_LIB = REMOTE_HZCMD_ROOT + "/hz-lib";
    public static String REMOTE_GG_LIB = REMOTE_HZCMD_ROOT + "/gg-lib";
    */

    public static void install(BoxManager boxes, JvmFactory jvmFactory,  String... versions) throws IOException, InterruptedException {

        String mainJars = Bash.find(M2_Repo, "hzCmd-1.0.1.jar");
        String cacheJars = Bash.find(M2_Repo, "cache-api-1.0.0.jar");
        String guavaars = Bash.find(M2_Repo, "guava-15.0-rc1.jar");
        String hdr = Bash.find(M2_Repo, "HdrHistogram-2.1.8.jar");
        String mq = Bash.find(M2_Repo, "activemq-all-5.13.0.jar");
        String metrics = Bash.find(M2_Repo, "metrics-core-3.1.1.jar");
        String slf4j = Bash.find(M2_Repo, "slf4j-api-1.7.7.jar");

        boxes.mkdir(LIB);
        boxes.upload(mainJars, LIB);
        boxes.upload(cacheJars, LIB);
        boxes.upload(guavaars, LIB);
        boxes.upload(hdr, LIB);
        boxes.upload(mq, LIB);
        boxes.upload(metrics, LIB);
        boxes.upload(slf4j, LIB);

        for (String version : versions) {

            String uploadDir = jvmFactory.getVendorLibDir(version);
            boxes.mkdir(uploadDir);

            List<String> names = jvmFactory.getVendorLibNames(version);
            for (String name : names) {
                String jar = Bash.find(M2_Repo, name);
                boxes.upload(jar,  uploadDir);
            }
        }
    }

}