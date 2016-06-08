package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "interval", description = "set expected interval of ops in millis")
public class BenchInterval extends Command {

    @Arguments(description = "default max rate 1 operation per millisecond")
    public long interval=1;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchInterval(interval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
