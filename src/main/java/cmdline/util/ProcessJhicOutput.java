package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "processJhic", description = "process JHIC output")
public class ProcessJhicOutput extends Command {

    @Option(name = "-dir", description = "path to JHIC output data")
    public String dir;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.processJhicOutput(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}