package global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Bash {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String esc = ((char) 27) + "";
    public static final String li_blue = esc + "[1;34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String li_magenta = esc + "[1;35m";

    public static final String gcViewer = "gcviewer-1.34.1.jar";

    public static boolean showSSH=false;

    public static String killAllJava() throws IOException, InterruptedException {
        return executeCommand("killall -9 java");
    }

    public static String find(String path, String file) throws IOException, InterruptedException {
        return executeCommand("find " + path + " -name " + file);
    }

    public static String findArgs(String path, String args) throws IOException, InterruptedException {
        return executeCommand("find " + path + " " + args);
    }

    public static boolean findLocalError(String dir) throws IOException, InterruptedException {

        String err = findArgs(dir, "-name exception.txt -o -name *.hprof -o -name *.oome -o -name hs_err_pid*");

        if (err != null && !err.isEmpty()) {
            System.out.println(Bash.ANSI_RED + err + Bash.ANSI_RESET);
            return true;
        }
        return false;
    }


    public static String findShallow(String path, String file) throws IOException, InterruptedException {
        return executeCommand("find " + path + "-maxdepth 1 -name " +file);
    }


    public static String cp(String src, String dst) throws IOException, InterruptedException {
        return executeCommand("cp " + src + " " + dst);
    }


    public static void mkdir(String dir) throws IOException, InterruptedException {
        executeCommand("mkdir -p " + dir);
    }

    public static void chmodExe(String file) throws IOException, InterruptedException {
        executeCommand("chmod +x " + file);
    }

    public static String rmDir(String dir) throws IOException, InterruptedException {
        return executeCommand("rm -fr " + dir);
    }

    public static String rm(String file) throws IOException, InterruptedException {
        return executeCommand("rm " + file);
    }

    public static void rmQuite(String file) throws IOException, InterruptedException {
        executeCommandWithExitCode("rm "+file);
    }


    public static String ssh(String user, String ip, String cmd) throws IOException, InterruptedException {
        return executeCommand("ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no "+user+"@"+ip+" "+cmd);
    }

    public static void streamSsh(String user, String ip, String cmd) throws IOException, InterruptedException {
        executeForEverCommand("ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no "+user+"@"+ip+" "+cmd);
    }


    public static int sshWithExitCode(String user, String ip, String cmd) throws IOException, InterruptedException {
        return executeCommandWithExitCode("ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no "+user+"@"+ip+" "+cmd);
    }

    public static void scpUp(String user, String ip, String from, String to) throws IOException, InterruptedException {
        executeCommand("scp -r " + from + " " + user + "@" + ip + ":" + to);
    }

    public static void rsyncUp(String user, String ip, String from, String to) throws IOException, InterruptedException {
        executeCommand("rsync " + from + " " + user + "@" + ip + ":" + to);
    }


    public static void scpDown(String user, String ip, String from, String to) throws IOException, InterruptedException {
        mkdir(to);
        executeCommand("scp -r " + user + "@" + ip + ":" + from + " " + to + "/");
    }

    public static void chartGcLogs(String dir) throws IOException, InterruptedException{
        executeCommand("find "+dir+" -name verbosegc.log | xargs -n1 -I% sh -c \"java -jar $(find ~/.m2 -name "+gcViewer+") % %.csv %.png\" ");
    }


    public static String executeCommand(String command) throws IOException, InterruptedException {
        if (showSSH) {
            System.out.println(command);
        }

        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();
        int exitCode = p.exitValue();

        BufferedReader reader;
        if (exitCode != 0 ){
            System.out.println(Bash.ANSI_RED+"ERROR exit code = "+p.exitValue()+" cmd="+command+Bash.ANSI_RESET);
            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        }

        StringBuffer output = new StringBuffer();
        String line;
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }

        if (exitCode != 0) {
            System.out.println(Bash.ANSI_RED+output.toString()+Bash.ANSI_RESET);
        }

        return output.toString();
    }

    public static int executeCommandWithExitCode(String command) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();
        return p.exitValue();
    }

    private static void executeForEverCommand(String command) throws IOException, InterruptedException {
        if (showSSH) {
            System.out.println(command);
        }

        Process p = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while (true) {
            String line;
            if ((line = reader.readLine())!= null) {
                System.out.println(Bash.ANSI_PURPLE+line+Bash.ANSI_RESET);
                continue;
            }
            Thread.sleep(50);
        }

    }

}