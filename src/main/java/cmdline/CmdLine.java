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
                .withCommands(Help.class, Add.class, Cluster.class, Install.class, Kill.class);

        builder.withGroup("add")
                .withDescription("add")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddJvm.class);

        return builder.build();
    }
}
