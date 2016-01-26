package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "async", description = "invokeAsync Async a method with a given thread count on a class identified by taskId,  on a cluster/member/client")
public class Async extends Command
{
    @Option(name = "-id", description = "jvm id")
    public String jvmId;

    @Option(name = "-task", description = "taskId identifies the object to run the method on")
    public String taskId;

    @Option(name = "-f", description = "function / method to invokeAsync")
    public String function;

    @Option(name = "-threads", description = "thread count")
    public int threadCount=1;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.invokeAsync(jvmId, threadCount, function, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
