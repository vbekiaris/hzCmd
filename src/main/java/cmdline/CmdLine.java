package cmdline;

import com.github.rvesse.airline.*;
import com.github.rvesse.airline.annotations.*;
import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;
import local.HzCmd;

import java.util.List;

@SuppressWarnings("unchecked")
public class CmdLine {

    @SuppressWarnings("unchecked")
    public static com.github.rvesse.airline.Cli<Command> getParser(){

        CliBuilder builder = new CliBuilder("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, CmdLine.Add.class, CmdLine.Cluster.class, CmdLine.Install.class, CmdLine.Kill.class);

        builder.withGroup("add")
                .withDescription("add")
                .withDefaultCommand(Help.class)
                .withCommands(CmdLine.AddBox.class, CmdLine.AddJvm.class);

        return builder.build();
    }

    public static class Command implements Runnable {
        public void run() {
            System.out.println(getClass().getSimpleName());
        }

        public void exe(HzCmd hzCmd) {
            System.out.println(hzCmd);
        }
    }


    @com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
    public static class Cluster extends Command {

        @Option(name = "-id", description = "variable name of cluster used as handle to a cluster")
        public String user;

        @Option(name = "-start", description = "start idx into boxes file")
        public int start_idx = 0;

        @Option(name = "-last", description = "end idx into boxes files")
        public int end_idx = 0;
    }


    @com.github.rvesse.airline.annotations.Command(name="install", description = "install Hazelcast version's onto boxes in cluster")
    public static class Install extends Command {

        @Option(name = "-cluster", description = "default * (install to all defined clusters)")
        public String cluster = "*";

        @Option(name = "-ee", description = "default false Hazelcast enterprise")
        public boolean ee = false;

        @Arguments( description = "list of Hazelcast version for upload to target clusters" )
        public List<String> versions;
    }



    @com.github.rvesse.airline.annotations.Command(name="add", description = "")
    public static class Add extends Command { }

    @com.github.rvesse.airline.annotations.Command(name = "boxes", description = "file of ip address and ssh login user name")
    public static class AddBox extends Command {

        @Option(name = "-u", description = "default ec2-user")
        public String user = "admin";

        @Option(name = "-f", description = "default agents.txt")
        public String file = "agents.txt";

        public void run() {
            System.out.println(getClass().getSimpleName() + " " + user + " " + file);
        }

        public void exe(HzCmd hzCmd) {
            hzCmd.addBoxes(user, file);
            System.out.println(hzCmd);
        }
    }

    @com.github.rvesse.airline.annotations.Command(name = "jvm", description = "Add member/client jvm's to a cluster")
    public static class AddJvm extends Command {

        @Option(name = "-m", description = "default 0")
        public int member = 0;

        @Option(name = "-c", description = "default 0")
        public int client = 0;

        @Option(name = "-cluster", description = "default *")
        public String cluster = "*";

        @Option(name = "-v", description = "hazelcast version e.g. 9.7")
        public String version;

        @Option(name = "-o", description = "jvm options")
        public String options;

        public void run() {
            System.out.print(getClass().getSimpleName());
        }
    }

    @com.github.rvesse.airline.annotations.Command(name = "kill", description = "kill -9 cluster/members/clients")
    public static class Kill extends Command
    {
        @Option(name = "-cluster", description = "cluster id to kill, * for ALL")
        public String cluster;

        @Option(name = "-m", description = "member id to kill, * for ALL")
        public String member;

        @Option(name = "-c", description = "client id to kill, * for ALL")
        public String client;

        public void run() {

        }
    }


}
