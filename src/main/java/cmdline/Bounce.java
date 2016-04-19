package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "bounce", description = "bounce jvm's matching id pattern")
public class Bounce extends Command
{

    @Option(name = "-id", description = "default .*")
    public String jvmId=".*";

    @Option(name = "-initalDelay", description = "initial delay second default 60")
    public int initalDelaySec=60;


    @Option(name = "-delay", description = "restart delay second default 30")
    public int delaySec=30;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.bounce(jvmId, initalDelaySec, delaySec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
