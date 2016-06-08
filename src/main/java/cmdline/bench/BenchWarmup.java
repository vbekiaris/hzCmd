package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "warmup", description = "benchmark warmup duration seconds")
public class BenchWarmup extends Command {

    @Arguments(description = "default 30")
    public int warmup=30;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchWarmupSec(warmup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
