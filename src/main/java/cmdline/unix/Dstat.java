package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "dstat", description = "start dstat on boxes")
public class Dstat extends Command
{

    @Arguments(description = "add arg to stop dstat")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null) {
                hzCmd.startDstat();
            }else {
                hzCmd.stopDstat();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
