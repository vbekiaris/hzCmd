package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "UploadCwd", description = "upload to lib dir")
public class UploadLib extends Command {

    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-src", description = "default ec2-user")
    public String src = "admin";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.uploadLib(jvmId, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}