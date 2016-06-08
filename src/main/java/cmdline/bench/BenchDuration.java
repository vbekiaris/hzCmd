package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "bench", description = "benchmark duration seconds")
public class BenchDuration extends Command {

    @Arguments(description = "default 60")
    public int duration = 60;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchBenchDurationSec(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
