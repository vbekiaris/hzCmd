package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "exit", description = "system.exit on jvm's in cluster/members/clients")
public class Exit extends Command
{
    @Option(name = "-cluster", description = "cluster ")
    public String cluster;

    @Option(name = "-m", description = "member")
    public String member;

    @Option(name = "-c", description = "client")
    public String client;


    public void exe(HzCmd hzCmd) {

        System.out.println(this);

        if(client!=null){
            try {
                hzCmd.exit(cluster, client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(member!=null){
            try {
                hzCmd.exit(cluster, member);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Exit{" +
                "client='" + client + '\'' +
                ", cluster='" + cluster + '\'' +
                ", member='" + member + '\'' +
                '}';
    }
}
