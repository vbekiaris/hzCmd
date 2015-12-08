package global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Bash {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


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
        return executeCommand("ssh "+user+"@"+ip+" "+cmd);
    }

    public static int sshWithExitCode(String user, String ip, String cmd) throws IOException, InterruptedException {
        return executeCommandWithExitCode("ssh "+user+"@"+ip+" "+cmd);
    }

    public static void scpUp(String user, String ip, String from, String to) throws IOException, InterruptedException {
        executeCommand("scp -r " + from + " " + user + "@" + ip + ":" + to);
    }

    public static void scpDown(String user, String ip, String from, String to) throws IOException, InterruptedException {
        mkdir(to+"/"+ip+"/");
        executeCommand("scp -r +" + user + "@" + ip + ":" + from + " " + to + "/" + ip + "/");
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
            System.out.println("ERROR exit code = "+p.exitValue()+" cmd="+command);
            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        }

        StringBuffer output = new StringBuffer();
        String line;
        while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
        }

        if(showSSH){
            System.out.println(output.toString());
        }
        else if (exitCode != 0) {
            System.out.println(command);
            System.out.println(output.toString());
        }

        return output.toString();
    }

    private static int executeCommandWithExitCode(String command) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();
        return p.exitValue();
    }
}