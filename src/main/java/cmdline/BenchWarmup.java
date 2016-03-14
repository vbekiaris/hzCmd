package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "warmup", description = "comma delimited list of benchMark thread counts")
public class BenchWarmup extends Command {

    @Arguments(description = "e.g. 8,16,32 ")
    public int warmup;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchWarmupSec(warmup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
