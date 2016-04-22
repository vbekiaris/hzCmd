package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "download", description = "download all files from jvm's with name matching id regex")
public class Download extends Command
{
    @Option(name = "-id", description = "regex to match")
    public String id=".*";

    @Arguments( description = "dest dir default ./output/" )
    public String dir = "output";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.download(id, dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
