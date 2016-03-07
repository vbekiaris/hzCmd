package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "driver", description = "invoke each stage of a benchmark provided the task all point to a class which extends remote.BenchRun")
public class BenchDriver extends Command {

    @Arguments(description = "comma delimited list of benchMark drivers")
    public String drivers;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchDrivers(drivers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
