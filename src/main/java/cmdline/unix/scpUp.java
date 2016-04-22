package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "scpUp", description = "file of ip address and ssh login user name")
public class scpUp extends Command {

    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-src", description = "default ec2-user")
    public String src = "admin";

    @Option(name = "-dst", description = "default agents.txt")
    public String dst = "agents.txt";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.scpUp(jvmId, src, dst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}