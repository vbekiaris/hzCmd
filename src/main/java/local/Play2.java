package local;


import io.airlift.airline.*;

import java.util.List;

public class Play2 {

    public static void main(String[] args)
    {
        Cli.CliBuilder<Runnable> builder = Cli.<Runnable>builder("Play2")
                .withDescription("cluster cmd line")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Add.class);

        builder.withGroup("add")
                .withDescription("add")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddMember.class, AddClient.class);

        builder.withGroup("kill")
                .withDescription("kill")
                .withDefaultCommand(KillAllClusters.class)
                .withCommands(KillCluster.class);

        Cli<Runnable> gitParser = builder.build();
        args = new String[4];
        args[0]="add";
        args[1]="boxes";
        args[2]="danny";
        args[3]="agents.txt";
        gitParser.parse(args).run();

        args = new String[6];
        args[0]="add";
        args[1]="member";
        args[2]="2";
        args[3]="A";
        args[4]="3.6-RC2-SNAPSHOT";
        args[5]="-Xms200m -Xmx1G";
        gitParser.parse(args).run();

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

        args = new String[4];
        args[0]="kill";
        args[1]="cluster";
        args[2]="A";
        args[3]="Member1";
        gitParser.parse(args).run();
    }

    public static class Command implements Runnable
    {
        public void run() {
            System.out.println(getClass().getSimpleName());
        }
    }

    @io.airlift.airline.Command(name = "add", description = "Add file contents to the index")
    public static class Add extends Command
    {
        @Arguments(description = "Patterns of files to be added")
        public List<String> patterns;
    }

    @io.airlift.airline.Command(name = "boxes", description = "Gives some information about the remote <name>")
    public static class AddBox extends Command
    {
        @Arguments(description = "Remote to show")
        public List<String> args;

        public void run() {
            System.out.print(getClass().getSimpleName());
            for (String arg : args) {
                System.out.print(" "+arg);
            }
            System.out.println();
        }
    }

    @io.airlift.airline.Command(name = "member", description = "Adds a remote")
    public static class AddMember extends Command
    {
        @Arguments( description = "Remote repository to add" )
        public List<String> args;

        public void run() {
            System.out.print(getClass().getSimpleName());
            for (String arg : args) {
                System.out.print(" "+arg);
            }
            System.out.println();
        }
    }

    @io.airlift.airline.Command(name = "client", description = "Adds a remote")
    public static class AddClient extends Command
    {
        @Arguments( description = "Remote repository to add" )
        public List<String> args;

        public void run() {
            System.out.print(getClass().getSimpleName());
            for (String arg : args) {
                System.out.print(" "+arg);
            }
            System.out.println();
        }
    }

    @io.airlift.airline.Command(name = "", description = "kill all cluster")
    public static class KillAllClusters extends Command
    {
        @Arguments( description = "Remote repository to add" )
        public List<String> args;

        public void run() {
            System.out.print(getClass().getSimpleName());
            System.out.println();
        }
    }

    @io.airlift.airline.Command(name = "cluster", description = "Adds a remote")
    public static class KillCluster extends Command
    {
        @Arguments( description = "Remote repository to add" )
        public List<String> args;

        public void run() {
            System.out.print(getClass().getSimpleName());
            for (String arg : args) {
                System.out.print(" "+arg);
            }
            System.out.println();
        }
    }
}
