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
                              Clean.class, Wipe.class, Load.class);

        builder.withGroup("add")
                .withDescription("add")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, Cluster.class, AddJvm.class);

        return builder.build();
    }
}
