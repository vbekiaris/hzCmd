package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "dstat", description = "start dstat on boxes")
public class Dstat extends Command
{

    @Arguments(description = "add arument to stop dstat")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {

            if(ids.size()==1) {
                hzCmd.stopDstat();
            }
            hzCmd.startDstat();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
