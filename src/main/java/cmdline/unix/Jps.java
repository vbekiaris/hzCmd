package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "jps", description = "jps")
public class Jps extends Command
{
    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.jps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
