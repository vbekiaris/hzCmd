package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;


@com.github.rvesse.airline.annotations.Command(name = "memberBox", description = "set number of dedicated member boxes for a cluster")
public class MemberBox extends Command {

    @Option(name = "-cluster", description = "cluster id, * for ALL")
    public String cluster;

    @Option(name = "-count", description = "number of boxes to contain only member jvm's in a cluster, default is 0")
    public int count = 0;

    public void exe(HzCmd hzCmd) {
        hzCmd.dedicatedMembers(cluster, count);
    }
}