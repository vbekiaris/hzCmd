package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "sync", description = "invoke sync a method with a given thread count on a class identified by taskId,  on a cluster/member/client")
public class Sync extends Command
{
    @Option(name = "-cluster", description = "cluster id")
    public String cluster;

    @Option(name = "-s", description = "selection")
    public String selection;

    @Option(name = "-t", description = "thread count")
    public int threadCount;

    @Option(name = "-f", description = "function / method to invokeAsync")
    public String function;

    @Option(name = "-i", description = "taskId identifies the object to run the method on")
    public String taskId;



    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.invokeAsync(cluster, selection, threadCount, function, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
