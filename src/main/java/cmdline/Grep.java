package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "grep", description = "cat cluster/members/clients")
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
