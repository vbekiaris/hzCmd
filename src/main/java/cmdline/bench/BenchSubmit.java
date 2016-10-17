package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "run", description = "run a benchmark")
public class BenchSubmit extends Command
{
    @Option(name = "-id", description = "cluster's to run the benchmarks, regex on cluster id ")
    public String clusterId=".*";

    @Arguments(description = "benchmark files")
    public List<String> benchMarkFiles;

    public void exe(HzCmd hzCmd) {
        try {
            for (String bench : benchMarkFiles) {
                hzCmd.submitBenchMark(clusterId, bench);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
