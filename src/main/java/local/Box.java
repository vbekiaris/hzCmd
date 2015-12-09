package local;

import global.Bash;

import java.io.IOException;

public class Box {
    public String user;
    public String pub;
    public String pri;

    public Box(String user, String pub, String pri){
        this.user=user;
        this.pub=pub;
        this.pri=pri;
    }

    public void upload(String souce, String dest) throws IOException, InterruptedException {
        Bash.scpUp(user, pub, souce, dest);
    }

    public void downlonad(String souce, String dest) throws IOException, InterruptedException {
        Bash.scpDown(user, pub, souce, dest);
    }

    public int sshWithExitCode(String cmd) throws IOException, InterruptedException {
        return Bash.sshWithExitCode(user, pub, cmd);
    }

    public String ssh(String cmd) throws IOException, InterruptedException {
        return Bash.ssh(user, pub, cmd);
    }

    public void mkdir(String arg) throws IOException, InterruptedException {
        ssh("mkdir -p " + arg);
    }

    public void rm(String arg) throws IOException, InterruptedException {
        ssh("rm -fr " + arg);
    }

    public void killHard(int pid) throws IOException, InterruptedException {
        ssh("kill -9 "+pid);
    }

    public String cat(String arg) throws IOException, InterruptedException {
        return  ssh("cat " +arg);
    }

    public String tail(String arg) throws IOException, InterruptedException {
        return ssh("tail "+arg);
    }

    public String grep(String arg) throws IOException, InterruptedException {
        return ssh("grep " + arg);
    }

    public String jps() throws IOException, InterruptedException {
        return ssh("jps");
    }

    public String toString() {
        return "Box{" +
                "user="+user+
                ",pub=" + pub +
                ", pri=" + pri +
                '}';
    }
}

