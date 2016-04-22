package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "interval", description = "set the expected interval in millis")
public class BenchInterval extends Command {

    @Arguments(description = "default value 1")
    public long interval=1;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchInterval(interval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
