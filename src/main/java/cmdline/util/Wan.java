package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "wam", description = "init hz wan rep xml")
public class Wan extends Command
{
    @Arguments(description = "cluster id's")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.wanXml(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
