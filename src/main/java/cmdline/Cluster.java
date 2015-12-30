package cmdline;

import com.github.rvesse.airline.annotations.Option;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "declare id for a cluster, and the set of boxes in a cluster")
public  class Cluster extends Command {

    @Option(name = "-id", description = "variable name of cluster used as handle to a cluster")
    public String user;

    @Option(name = "-start", description = "start idx into boxes file")
    public int start_idx = 0;

    @Option(name = "-last", description = "end idx into boxes files")
    public int end_idx = 0;
}
