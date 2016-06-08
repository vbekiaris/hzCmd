package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "uploadCwd", description = "upload to jvm cwd")
public class UploadCwd extends Command {

    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-src", description = "default ec2-user")
    public String src = "admin";

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.uploadCwd(jvmId, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}