package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "threads", description = "comma delimited list of benchMark thread counts")
public class BenchThreads extends Command {

    @Arguments(description = "e.g. 8,16,32 ")
    public String threadsCount;


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBenchThreadCounts(threadsCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
