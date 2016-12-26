package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "randMember", description = "id of random member")
public class RandMember extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    public void exe(HzCmd hzCmd) {
        try {

            hzCmd.randMemberId(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
