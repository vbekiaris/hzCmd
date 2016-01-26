package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "ssh", description = "ssh instruction from cwd of each member client")
public class Ssh extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    @Option(name = "-cmd", description = "jvm id / name")
    public String cmd = "pwd";


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.ssh(jvmId, cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
