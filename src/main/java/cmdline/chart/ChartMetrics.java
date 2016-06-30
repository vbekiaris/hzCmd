package cmdline.chart;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "metrics", description = "chart csv files produced by Metrics bench style")
public class ChartMetrics extends Command {

    @Option(name = "-dir", description = "path to download data")
    public String dir="output";


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.chartAllJavaMetrics(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}