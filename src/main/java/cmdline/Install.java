package cmdline;


import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name="install", description = "install Hazelcast version's onto boxes in cluster")
public class Install extends Command {

    @Option(name = "-cluster", description = "default * (install to all defined clusters)")
    public String cluster = "*";

    @Option(name = "-ee", description = "default false Hazelcast enterprise")
    public boolean ee = false;

    @Arguments( description = "list of Hazelcast version for upload to target clusters" )
    public List<String> versions;
}
