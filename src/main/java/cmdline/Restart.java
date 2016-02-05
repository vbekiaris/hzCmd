package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "restart", description = "restart a jvm in a cluster using its id")
public class Restart extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Arguments(description = "jvm options")
    public String options;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.restart(jvmId, version, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
