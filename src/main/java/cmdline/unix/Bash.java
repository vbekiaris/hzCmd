package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "bash", description = "run cmd on each id")
public class Bash extends Command
{
    @Option(name = "-id", description = "cluster id default .*")
    public String clusterId=".*";

    @Option(name = "-jvmId", description = "jvm id default .*")
    public String jvmId=".*";

    @Arguments(description = "rum cmd")
    public List<String> args;

    public void exe(HzCmd hzCmd) {
        try {
            if(args!=null){
                String cmd = new String();
                for (String arg : args) {
                    cmd +=arg+" ";
                }
                cmd = cmd.trim();

                hzCmd.bashCmd(clusterId, jvmId, cmd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
