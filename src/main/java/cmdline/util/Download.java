package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "download", description = "download cwd of jvm's in cluster")
public class Download extends Command
{
    @Arguments( description = "dest dir default ./output/" )
    public String dir = "output";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.download(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
