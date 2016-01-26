package cmdline;


import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name="install", description = "install Hazelcast version's onto boxes in cluster")
public class Install extends Command {

    @Option(name = "-cluster", description = "default * (install to all defined clusters)")
    public String cluster = ".*";

    @Option(name = "-ee", description = "default false Hazelcast enterprise")
    public boolean ee = false;

    @Arguments( description = "list of Hazelcast version for upload to target clusters" )
    public List<String> versions;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.install(cluster, ee, versions.toArray(new String[versions.size()]) );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
