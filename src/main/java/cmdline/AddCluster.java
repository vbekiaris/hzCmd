package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
public  class AddCluster extends Command {

    @Option(name = "-cluster", description = "set name of cluster")
    public String cluster;

    @Option(name = "-boxes", description = "name of boxes")
    public String boxes = "agents.txt";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.addCluster(cluster, boxes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
