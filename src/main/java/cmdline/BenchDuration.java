package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "duration", description = "comma delimited list of benchMark thread counts")
public class BenchDuration extends Command {

    @Arguments(description = "e.g. 60 ")
    public int duration ;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchBenchDurationSec(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
