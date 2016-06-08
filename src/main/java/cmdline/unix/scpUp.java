package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "scpUp", description = "upload src to dest")
public class scpUp extends Command {

    @Option(name = "-id", description = "jvm id")
    public String jvmId=".*";

    @Option(name = "-src", description = "src file ")
    public String src;

    @Option(name = "-dst", description = "dest on box")
    public String dst;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.scpUp(jvmId, src, dst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}