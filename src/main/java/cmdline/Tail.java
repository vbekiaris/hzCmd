package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "tail", description = "cat cluster/members/clients")
public class Tail extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.tail(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
