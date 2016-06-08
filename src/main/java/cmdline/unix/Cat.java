package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "cat", description = "cat jvm std out")
public class Cat extends Command
{
    @Option(name = "-id", description = "regex jvm id default .*")
    public String jvmId = ".*";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.cat(jvmId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
