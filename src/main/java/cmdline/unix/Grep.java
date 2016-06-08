package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "grep", description = "grep std out of jvm in cluster")
public class Grep extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Arguments( description = "grep args" )
    public String grepArgs;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.grep(jvmId, grepArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
