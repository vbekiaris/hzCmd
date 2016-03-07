package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "run", description = "invoke each stage of a benchmark provided the task all point to a class which extends remote.BenchRun")
public class BenchRun extends Command
{
    @Option(name = "-id", description = "jvm id / name where to run the benchmark, default all members and clients")
    public String jvmId=".*";

    @Option(name = "-task", description = "taskId, comer sepreated list of benchmars, to run in parellel")
    public String taskId;

    @Option(name = "-threads", description = "thread count to b used running the benchmark")
    public int threadCount=1;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.invokeBenchMark(jvmId, threadCount, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
