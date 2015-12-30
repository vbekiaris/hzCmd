package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.HzType;
import local.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "cat", description = "cat cluster/members/clients")
public class Grep extends Command
{
    @Option(name = "-cluster", description = "cluster id to kill, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id to kill, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id to kill, * for ALL")
    public String client;

    @Arguments( description = "grep args" )
    public String grepArgs;


    public void exe(HzCmd hzCmd) {

        if(client!=null){
            try {
                hzCmd.grep(cluster, HzType.Client + client, grepArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(member!=null){
            try {
                hzCmd.grep(cluster, HzType.Member + member, grepArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
