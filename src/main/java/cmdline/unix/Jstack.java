package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "jstack", description = "jstack")
public class Jstack extends Command
{
    @Option(name = "-id", description = "jvm id / name")
    public String jvmId = ".*";

    @Option(name = "-f", description = "jvm id / name")
    public String file = "jstack.txt";


    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.jstack(jvmId, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
