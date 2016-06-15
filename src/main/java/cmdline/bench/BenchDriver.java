package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "driver", description = "set which jvm's run the bench, comma delimited list jvmId's")
public class BenchDriver extends Command {

    @Arguments(description = "comma delimited list of benchMark drivers")
    public String drivers="Member";


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchDrivers(drivers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
