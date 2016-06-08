package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "libs", description = "upload to lib dir of a cluster")
public class UploadLib extends Command {

    @Option(name = "-cluster", description = "cluster id")
    public String cluster;

    @Option(name = "-src", description = "src path")
    public String src;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.uploadLib(cluster, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}