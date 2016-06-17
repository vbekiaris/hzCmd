package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "wipe", description = "killall -9 java on all box;  rm -fr hzCmd home dir")
public class Wipe extends Command
{
    @Option(name = "-local", description = "when running locally")
    public boolean local = false;


    public void exe(HzCmd hzCmd) {
        try {
            if (local){
                hzCmd.wipeLocal();
            }else {
                hzCmd.wipe();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
