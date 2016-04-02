package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "sync", description = "invoke sync a method with a given thread count on a class identified by taskId,  on a cluster/member/client")
public class Sync extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId;

    @Option(name = "-task", description = "taskId identifies the object to run the method on")
    public String taskId;

    @Option(name = "-f", description = "function / method to invokeAsync")
    public String function;

    @Option(name = "-threads", description = "thread count")
    public int threadCount=1;

    public void exe(HzCmd hzCmd) {
        try {

            throw new RuntimeException("not valid any more ");
            //hzCmd.invokeSync(jvmId, threadCount, function, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
