package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "ls", description = "ls the cwd of jvm in cluster")
public class Ls extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.ls(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
