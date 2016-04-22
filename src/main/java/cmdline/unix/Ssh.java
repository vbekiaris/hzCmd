package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "ssh", description = "ssh instruction from cwd of each member client")
public class Ssh extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    @Option(name = "-cmd", description = "jvm id / name")
    public String cmd = "ls";

    @Arguments(description = "ssh cmd string")
    private List<String> args;


    public void exe(HzCmd hzCmd) {
        try {
            StringBuilder cmd = new StringBuilder();
            for (String s : args)
                cmd.append(s+" ");

            hzCmd.ssh(jvmId, cmd.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
