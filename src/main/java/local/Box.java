package local;

import global.Bash;

import java.io.IOException;
import java.io.Serializable;

import static global.Utils.escapeQuotes;

public class Box implements Serializable{
    public String user;
    public String pub;
    public String pri;

    public Box(String user, String pub, String pri){
        this.user=user;
        this.pub=pub;
        this.pri=pri;
    }

    public void upload(String souce, String dest) throws IOException, InterruptedException {
        //Bash.scpUp(user, pub, souce, dest);
        Bash.rsyncUp(user, pub, souce, dest);
    }

    public void downlonad(String souce, String dest) throws IOException, InterruptedException {
        Bash.scpDown(user, pub, souce, dest);
    }

    public int sshWithExitCode(String cmd) throws IOException, InterruptedException {
        return Bash.sshWithExitCode(user, pub, cmd);
    }

    public void streamSsh(String cmd) throws IOException, InterruptedException {
         Bash.streamSsh(user, pub, cmd);
    }

    public String ssh(String cmd) throws IOException, InterruptedException {
        return Bash.ssh(user, pub, cmd);
    }

    public boolean testConnecton() throws IOException, InterruptedException {
        return Bash.sshWithExitCode(user, pub, "pwd") == 0;
    }


    public void mkdir(String arg) throws IOException, InterruptedException {
        ssh("mkdir -p " + arg);
    }

    public void rm(String arg) throws IOException, InterruptedException {
        ssh("rm -fr " + arg);
    }

    public String killHard(int pid) throws IOException, InterruptedException {
        return ssh("kill -9 "+pid);
    }

    public void killAllJava() throws IOException, InterruptedException {
        ssh("killall -9 java");
    }

    public String cat(String arg) throws IOException, InterruptedException {
        return ssh("cat " +arg);
    }

    public String jstack(String dir, int pid, String file) throws IOException, InterruptedException {
        return ssh("cd "+dir+"; nohup jstack "+pid+" >> "+file+" &");
    }

    public String find(String dir, String name) throws IOException, InterruptedException {
        return ssh("find "+dir+" -name " +name);
    }

    public void tail(String arg) throws IOException, InterruptedException {
         streamSsh("tail -f "+arg);
    }

    public String grep(String arg) throws IOException, InterruptedException {

        return ssh("grep " + escapeQuotes(arg));
    }

    public String jps() throws IOException, InterruptedException {
        return ssh("jps");
    }

    public void scpUp(String from, String to) throws IOException, InterruptedException {
        Bash.scpUp(user, pub, from, to);
    }

    public void scpDown(String from, String to) throws IOException, InterruptedException {
        Bash.scpDown(user, pub, from, to);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Box.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Box other = (Box) obj;

        return pub.equals(other.pub);
    }

    public String toString() {
        return "Box{" +
                "user="+user+
                ", pub=" + pub +
                ", pri=" + pri +
                '}';
    }

}

