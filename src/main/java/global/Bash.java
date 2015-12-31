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

    public static boolean showSSH=true;

    public static String killAllJava() throws IOException, InterruptedException {
        return executeCommand("killall -9 java");
    }

    public static String find(String path, String file) throws IOException, InterruptedException {
        return executeCommand("find " + path + " -name " + file);
    }

    public static void mkdir(String dir) throws IOException, InterruptedException {
        executeCommand("mkdir -p " + dir);
    }

    public static String rmDir(String dir) throws IOException, InterruptedException {
        return executeCommand("rm -fr " + dir);
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

    public static void scpDown(String user, String ip, String from, String to) throws IOException, InterruptedException {
        mkdir(to);
        executeCommand("scp -r " + user + "@" + ip + ":" + from + " " + to + "/");
    }



    private static String executeCommand(String command) throws IOException, InterruptedException {
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

    private static int executeCommandWithExitCode(String command) throws IOException, InterruptedException {
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

        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(Bash.ANSI_PURPLE+line+Bash.ANSI_RESET);
        }
    }

}