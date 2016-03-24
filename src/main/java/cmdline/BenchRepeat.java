package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "repeat", description = "repeat count ")
public class BenchRepeat extends Command {

    @Arguments(description = "e.g. 60 ")
    public int repeat=1;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setRepeatCount(repeat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
