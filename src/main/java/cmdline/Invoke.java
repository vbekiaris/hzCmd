package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "invoke", description = "invoke a method with a given thread count on a class identified by taskId,  on a cluster/member/client")
public class Invoke extends Command
{
    @Option(name = "-cluster", description = "cluster id")
    public String cluster;

    @Option(name = "-m", description = "member id * for ALL")
    public String member;

    @Option(name = "-c", description = "client id * for ALL")
    public String client;

    @Option(name = "-t", description = "thread count")
    public int threadCount;

    @Option(name = "-f", description = "function / method to invoke")
    public String function;

    @Option(name = "-i", description = "taskId identifies the object to run the method on")
    public String taskId;



    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.invoke(cluster, NodeType.Member + member, threadCount, function, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
