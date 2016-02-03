package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "ping", description = "ping a cluster's/member's/client's")
public class Ping extends Command
{
    @Option(name = "-id", description = "jvm id / name default all")
    public String jvmId=".*";

    @Option(name = "-timeout", description = "ping timeout")
    public long timeout=1000;

    public void exe(HzCmd hzCmd) {
        hzCmd.ping(jvmId, timeout);
    }
}
