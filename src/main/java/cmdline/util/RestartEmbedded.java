package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "embeddedRestart", description = "restart embedded vendor object")
public class RestartEmbedded extends Command
{
    @Option(name = "-id", description = "jvm id")
    public String jvmId=".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.restartEmbeddedObject(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
