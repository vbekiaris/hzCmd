package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
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
