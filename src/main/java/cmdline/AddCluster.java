package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.ClusterType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
public  class AddCluster extends Command {

    @Option(name = "-cluster", description = "set name of cluster")
    public String cluster;

    @Option(name = "-boxes", description = "name of boxes")
    public String boxes = "agents.txt";

    @Option(name = "-type", description = "type of cluster ClusterType.HZ ClusterType.GG" )
    public String type = "HZ";


    public void exe(HzCmd hzCmd) {
        try {

            ClusterType t = ClusterType.valueOf(type);

            hzCmd.addCluster(cluster, boxes, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
