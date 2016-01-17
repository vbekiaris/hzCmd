package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.HzType;
import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "cat", description = "cat cluster/members/clients")
public class Invoke extends Command
{
    @Option(name = "-cluster", description = "cluster id to kill, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id to kill, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id to kill, * for ALL")
    public String client;

    @Option(name = "-t", description = "thread count")
    public int threadCount;

    @Option(name = "-f", description = "function ")
    public String function;

    @Option(name = "-i", description = "taskId")
    public String taskId;



    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.invoke(cluster, HzType.Member + member, threadCount, function, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
