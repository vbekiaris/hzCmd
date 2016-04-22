package cmdline.chart;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "compairMetrics", description = "chart csv files produced by Metrics bench style")
public class ChartMetricsCompair extends Command {

    @Option(name = "-dir", description = "path to download data")
    public String dir;

    @Option(name = "-red", description = "red lines")
    public String red;

    @Option(name = "-blue", description = "blue lines")
    public String blue;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.chartComparisonMetrics(dir, red, blue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}