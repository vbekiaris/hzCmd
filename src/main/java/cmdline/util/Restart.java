package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "restart", description = "restart a jvm in a cluster using its id")
public class Restart extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Option(name = "-ee", description = "ee version")
    public boolean ee=false;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.restart(jvmId, version, ee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
