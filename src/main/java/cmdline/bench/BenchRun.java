package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "run", description = "invoke each stage of a benchmark provided the task all point to a class which extends remote.BenchRun")
public class BenchRun extends Command
{
    @Option(name = "-cluster", description = "cluster id / name where to run the benchmark, default all members and clients")
    public String clusterId=".*";

    @Option(name = "-b", description = "taskId, comer sepreated list of benchmars, to run in parellel")
    public String bench;

    @Option(name = "-warmup", description = "run a warmup [true|false] default true")
    public String warmup="true";


    public void exe(HzCmd hzCmd) {
        try {
            boolean warmupBool = Boolean.parseBoolean(warmup);
            hzCmd.invokeBenchMarks(clusterId, bench, warmupBool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
