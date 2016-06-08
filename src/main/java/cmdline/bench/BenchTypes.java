package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "type", description = "type of benchmark tool used to measure")
public class BenchTypes extends Command {

    @Arguments(description = "[METRICS|HDR] default METRICS")
    public String types="METRICS";


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchType(types);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
