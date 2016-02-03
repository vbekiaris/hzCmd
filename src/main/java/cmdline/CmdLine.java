package cmdline;

import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;

@SuppressWarnings("unchecked")
public class CmdLine {

    @SuppressWarnings("unchecked")
    public static com.github.rvesse.airline.Cli<Runnable> getParser(){

        CliBuilder builder = new CliBuilder("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Info.class, Add.class, Set.class, MemberBox.class, Install.class,
                              Kill.class, Restart.class, Cat.class, Tail.class, Grep.class, Download.class,
                              Clean.class, Wipe.class, Load.class, Async.class, Sync.class, Exit.class, Listen.class,
                              Bench.class, Ssh.class, Ping.class, scpUp.class, UploadCwd.class, UploadLib.class);

        builder.withGroup("add")
                .withDescription("add boxes, clusters, members clients")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddCluster.class, AddMember.class, AddClient.class);

        return builder.build();
    }
}