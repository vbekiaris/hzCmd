package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "check", description = "check remote jvm for error, returns exit code 1 at error")
public class Check extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    public void exe(HzCmd hzCmd) {
        try {
            boolean error = hzCmd.printErrors(jvmId);
            if(error){
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
