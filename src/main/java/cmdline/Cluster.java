package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
public  class Cluster extends Command {

    @Option(name = "-id", description = "variable name of cluster used as handle to a cluster")
    public String id;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.addCluster(id, "box");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
