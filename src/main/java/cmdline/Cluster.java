package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
public  class Cluster extends Command {

    @Option(name = "-id", description = "variable name of cluster used as handle to a cluster")
    public String id;

    @Option(name = "-start", description = "start idx into boxes file")
    public int start = 0;

    @Option(name = "-end", description = "end idx into boxes files")
    public int end = 0;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.cluster(id, "box", start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
