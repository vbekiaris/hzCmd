package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "bounce", description = "bounce jvm's matching id pattern")
public class Bounce extends Command
{
    @Option(name = "-id", description = "default .*")
    public String jvmId=".*";

    @Option(name = "-duration", description = "duration seconds of the bouncing default 10 (gives 1 iteration)")
    public int duration=10;

    @Option(name = "-initalDelay", description = "initial delay seconds default 60")
    public int initalDelay=60;

    @Option(name = "-restartDelay", description = "restart delay second default 30")
    public int restartDelay=30;

    @Option(name = "-iterationDelay", description = "next iteration delay second default 30")
    public int iterationDelay=30;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.bounce(jvmId, duration, initalDelay, restartDelay, iterationDelay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
