package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "processJhic", description = "process jhic output")
public class ProcessJhicOutput extends Command {

    @Option(name = "-dir", description = "path to jhic output data")
    public String dir;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.processJhicOutput(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}