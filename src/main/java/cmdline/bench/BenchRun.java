package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "run", description = "run a benchmark")
public class BenchRun extends Command
{
    @Option(name = "-id", description = "cluster to run the benchmark, regex on cluster id ")
    public String clusterId=".*";

    @Option(name = "-warmup", description = "run a warmup [true|false] default true")
    public String warmup="true";

    @Arguments(description = "benchmark files")
    public List<String> benchMarkFiles;


    public void exe(HzCmd hzCmd) {
        try {
            boolean warmupBool = Boolean.parseBoolean(warmup);
            for (String bench : benchMarkFiles) {
                hzCmd.invokeBenchMark(clusterId, bench, warmupBool);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
