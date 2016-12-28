package local;

import global.Bash;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Installer {

    public static final String REMOTE_HZCMD_ROOT = "hz-root";
    public static final String REMOTE_HZCMD_ROOT_LIB = REMOTE_HZCMD_ROOT+"/" + "lib";

    public static final String REMOTE_HZCMD_ROOT_FULL_PATH ="$HOME/"+REMOTE_HZCMD_ROOT;
    public static final String REMOTE_HZCMD_LIB_FULL_PATH = "$HOME/"+ REMOTE_HZCMD_ROOT_LIB;

    private static final String HOME = "HOME";
    private static final String M2_DIR = "/.m2";
    private static final String M2_Repo = System.getenv(HOME)+M2_DIR;
    private static final String STASH = System.getenv("HZ_CMD_SRC")+"/stash";
    private static final String CWD = System.getProperty("user.dir");

    public static void install(BoxManager boxes, JvmFactory jvmFactory, boolean ee, String[] versions, String libFiles) throws IOException, InterruptedException {
        installCore(boxes);
        installVendorLibs(boxes, jvmFactory, ee, versions);
        installLibFiles(boxes, libFiles);
    }


    public static void installCore(BoxManager boxes) throws IOException, InterruptedException {
        System.out.println(Bash.ANSI_YELLOW + "Installing core" + Bash.ANSI_RESET);

        boxes.mkdir(REMOTE_HZCMD_ROOT_LIB);

        List<String> uploadStuff = new ArrayList<String>();

        uploadStuff.add(STASH + "/log4j.properties");

        uploadStuff.add(Bash.find(M2_Repo, "hzCmd-1.0.1.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "hzCmd-bench-1.0.0.jar\n"));
        uploadStuff.add(Bash.find(M2_Repo, "cache-api-1.0.0.jar"));

        uploadStuff.add(Bash.find(M2_Repo, "gson-2.7.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "jms-api-1.1-rev-1.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "activemq-client-5.13.3.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "geronimo-j2ee-management_1.1_spec-1.0.1.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "hawtbuf-1.11.jar"));


        uploadStuff.add(Bash.find(M2_Repo, "metrics-core-3.1.1.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "metrics-graphite-3.0.2.jar"));

        uploadStuff.add(Bash.find(M2_Repo, "HdrHistogram-2.1.9.jar"));

        uploadStuff.add(Bash.find(M2_Repo, "log4j-1.2.17.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "slf4j-api-1.7.5.jar"));
        uploadStuff.add(Bash.find(M2_Repo, "slf4j-log4j12-1.7.5.jar"));

        uploadStuff.add(Bash.find(M2_Repo, "lang-6.7.6.jar"));

        String files = new String();
        for (String file : uploadStuff) {
            files += file+" ";
        }
        boxes.upload(files, REMOTE_HZCMD_ROOT_LIB);
    }


    public static void installVendorLibs(BoxManager boxes, JvmFactory jvmFactory, boolean ee, String[] versions) throws IOException, InterruptedException {
        if ( versions != null) {
            for (String version : versions) {
                installVendorLib(boxes, jvmFactory, ee, version);
            }
        }
    }

    public static void installVendorLib(BoxManager boxes, JvmFactory jvmFactory, boolean ee, String version) throws IOException, InterruptedException {
        if(version!=null){
            String uploadDir = jvmFactory.getVendorLibDir(version);
            boxes.mkdir(uploadDir);

            List<String> names = jvmFactory.getVendorLibNames(version, ee);
            for (String name : names) {

                String jar = findFileincwd(name);
                if(jar==null) {
                     jar = Bash.find(M2_Repo, name);
                }

                if(jar==null || jar.length()==0){
                    System.out.println(Bash.ANSI_RED + "can't find "+name+" in cwd or "+M2_Repo+Bash.ANSI_RESET);
                    System.exit(1);
                }else{
                    System.out.println(Bash.ANSI_YELLOW + "installing "+jar.trim()+ Bash.ANSI_RESET);
                    boxes.upload(jar, uploadDir);
                }
            }
        }else{
            System.out.println(Bash.ANSI_RED + "version for jars is null" + Bash.ANSI_RESET);
            System.exit(1);
        }
    }


    public static void installLibFiles(BoxManager boxes, String libFiles) throws IOException, InterruptedException {
        if(libFiles!=null) {
            for (String file : libFiles.split(",")) {
                if(file!=null && file.length()!=0){
                    boxes.upload(file, REMOTE_HZCMD_ROOT_LIB);
                }
            }
        }
    }


    public static String findFileincwd(String fileName) {
        try {
            File root = new File(".");
            Collection files = FileUtils.listFiles(root, null, true);

            for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
                File file = iterator.next();

                if (file.getName().equals(fileName)){
                    return file.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}