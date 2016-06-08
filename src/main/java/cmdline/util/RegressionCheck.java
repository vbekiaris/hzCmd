package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "regression", description = "compare bench data, make a slack post")
public class RegressionCheck extends Command {

    @Option(name = "-dir", description = "path to download data")
    public String dir;

    @Option(name = "-red", description = "red lines")
    public String red;

    @Option(name = "-blue", description = "blue lines")
    public String blue;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.hdrRegresionCheck(dir, red, blue);
            hzCmd.metricsRegresionCheck(dir, red, blue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}