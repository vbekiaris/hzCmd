package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "repeat", description = "benchmark repeat count ")
public class BenchRepeat extends Command {

    @Arguments(description = "default 1")
    public int repeat=1;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setRepeatCount(repeat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
