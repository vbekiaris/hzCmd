package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "killall", description = "killall -9 java on all box")
public class KillAll extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.killAll(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
