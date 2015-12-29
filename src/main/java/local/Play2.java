package local;

import com.github.rvesse.airline.annotations.*;
import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;

import java.util.List;

@SuppressWarnings("unchecked")
public class Play2 {

    public static void main(String[] args)
    {
        CliBuilder<Runnable> builder = new CliBuilder<Runnable>("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Add.class, Cluster.class, Install.class, Kill.class);

        builder.withGroup("add")
                .withDescription("add")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddJvm.class);




        com.github.rvesse.airline.Cli<Runnable> gitParser = builder.build();

        /*
        args = new String[1];
        args[0]="help";
        gitParser.parse(args).run();

        args = new String[2];
        args[0]="help";
        args[1]="add";
        gitParser.parse(args).run();

        args = new String[3];
        args[0]="help";
        args[1]="add";
        args[2]="boxes";
        gitParser.parse(args).run();

        args = new String[6];
        args[0]="add";
        args[1]="boxes";
        args[2]="-u";
        args[3]="danny";
        args[4]="-f";
        args[5]="agents.txt";
        gitParser.parse(args).run();
        */

        args = new String[3];
        args[0]="help";
        args[1]="add";
        args[2]="boxes";
        gitParser.parse(args).run();


        args = new String[3];
        args[0]="help";
        args[1]="add";
        args[2]="jvm";
        gitParser.parse(args).run();


        args = new String[12];
        args[0]="add";
        args[1]="jvm";

        args[2]="-m";
        args[3]="2";

        args[4]="-c";
        args[5]="8";

        args[6]="-cluster";
        args[7]="A";

        args[8]="-v";
        args[9]="3.6-RC2-SNAPSHOT";

        args[10]="-o";
        args[11]="-Xms200m -Xmx1G";
        gitParser.parse(args).run();


        /*
        args = new String[6];
        args[0]="add";
        args[1]="client";
        args[2]="2";
        args[3]="A";
        args[4]="3.6-RC2-SNAPSHOT";
        args[5]="-Xms200m -Xmx1G";
        gitParser.parse(args).run();

        args = new String[1];
        args[0]="kill";
        gitParser.parse(args).run();

        args = new String[3];
        args[0]="kill";
        args[1]="cluster";
        args[2]="A";
        gitParser.parse(args).run();

        args = new String[3];
        args[0]="kill";
        args[1]="A";
        args[2]="Member1";
        gitParser.parse(args).run();

        args = new String[4];
        args[0]="kill";
        args[1]="cluster";
        args[2]="A";
        args[3]="Member*";
        gitParser.parse(args).run();
        */

    }

    public static class Command implements Runnable {
        public void run() {
            System.out.println(getClass().getSimpleName());
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
            System.out.print(getClass().getSimpleName()+" "+user+" "+file);
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
