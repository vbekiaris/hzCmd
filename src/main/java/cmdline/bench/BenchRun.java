package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.Bash;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "run", description = "run a benchmark")
public class BenchRun extends Command
{
    @Option(name = "-id", description = "cluster's to run the benchmarks, regex on cluster id ")
    public String clusterId=".*";

    @Arguments(description = "benchmark files")
    public List<String> benchMarkFiles;

    public void exe(HzCmd hzCmd) {
        try {

            if(benchMarkFiles==null){
                System.out.println(Bash.ANSI_RED + "-id "+clusterId+" NO benchmark files given" + Bash.ANSI_RESET);
                System.out.println(Bash.ANSI_RED + "EXIT 1" + Bash.ANSI_RESET);
                System.exit(1);
            }

            for (String bench : benchMarkFiles) {
                hzCmd.invokeBenchMark(clusterId, bench);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
