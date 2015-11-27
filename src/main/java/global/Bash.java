package global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Bash {

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

    public static void scpUp(String user, String ip, String from, String to) throws IOException, InterruptedException {
        executeCommand("scp -r " + from + " " + user + "@" + ip + ":" + to);
    }

    public static void scpDown(String user, String ip, String from, String to) throws IOException, InterruptedException {
        mkdir(to+"/"+ip+"/");
        executeCommand("scp -r +" + user + "@" + ip + ":" + from + " " + to + "/" + ip + "/");
    }



    private static String executeCommand(String command) throws IOException, InterruptedException {

        //System.out.println(command);

        StringBuffer output = new StringBuffer();

        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        int exitCode = p.exitValue();
        if (exitCode != 0 ){
            System.out.println("ERROR exit code = "+p.exitValue()+" cmd="+command);
            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        }

        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(line);
            output.append(line + "\n");
        }

        return output.toString();
    }
}