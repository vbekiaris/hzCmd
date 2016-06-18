package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "bounce", description = "bounce jvm's in cluster")
public class Bounce extends Command
{
    @Option(name = "-id", description = "default .*")
    public String jvmId=".*";

    @Option(name = "-iterations", description = "number of iteration default 1")
    public int iterations=1;

    @Option(name = "-initalDelay", description = "initial delay seconds default 0")
    public int initalDelay=0;

    @Option(name = "-restartDelay", description = "restart delay second default 10")
    public int restartDelay=10;

    @Option(name = "-iterationDelay", description = "next iteration delay second default 30")
    public int iterationDelay=30;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.bounce(jvmId, iterations, initalDelay, restartDelay, iterationDelay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
