package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "cat", description = "cat cluster/members/clients std out")
public class Cat extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.cat(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
