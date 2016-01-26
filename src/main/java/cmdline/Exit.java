package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "exit", description = "system.exit on jvm's in cluster/members/clients")
public class Exit extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.exit(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
