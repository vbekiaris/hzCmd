package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "download", description = "download all files from jvm working dir")
public class Download extends Command
{
    @Option(name = "-cluster", description = "cluster id, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id, * for ALL")
    public String client;

    @Arguments( description = "dest dir default ./output/" )
    public String dir = "output";

    public void exe(HzCmd hzCmd) {

        if(client!=null){
            try {
                hzCmd.downlonad(cluster, NodeType.Client + member, dir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(member!=null){
            try {
                hzCmd.downlonad(cluster, NodeType.Member + member, dir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
