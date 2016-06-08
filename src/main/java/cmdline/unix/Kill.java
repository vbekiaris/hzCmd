package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "kill", description = "kill -9 jvm in cluster")
public class Kill extends Command
{
    @Option(name = "-id", description = "jvm id default .*")
    public String jvmId=".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.kill(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
