package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "kill", description = "kill -9 cluster/members/clients")
public class Kill extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.kill(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
