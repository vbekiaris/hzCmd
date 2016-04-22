package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "clean", description = "clean cluster/members/clients")
public class Clean extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.clean(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
