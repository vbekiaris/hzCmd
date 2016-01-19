package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "tail", description = "cat cluster/members/clients")
public class Tail extends Command
{
    @Option(name = "-cluster", description = "cluster id to kill, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id to kill, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id to kill, * for ALL")
    public String client;


    public void exe(HzCmd hzCmd) {

        if(client!=null){
            try {
                hzCmd.tail(cluster, NodeType.Client + client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(member!=null){
            try {
                hzCmd.tail(cluster, NodeType.Member + member);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
