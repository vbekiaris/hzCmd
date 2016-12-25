package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "memberCount", description = "number of members in each cluster")
public class MemberCount extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    public void exe(HzCmd hzCmd) {
        try {

            hzCmd.memberCount(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
