package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.HzType;
import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "restart", description = "restart a jvm in a cluster using its id")
public class Restart extends Command
{
    @Option(name = "-cluster", description = "cluster id, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id, * for ALL")
    public String client;

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Arguments(description = "jvm options")
    public String options;

    public void exe(HzCmd hzCmd) {

        if(client!=null){
            try {
                hzCmd.restart(cluster, HzType.Client + client, version, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(member!=null){
            try {
                hzCmd.restart(cluster, HzType.Member + member, version, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
