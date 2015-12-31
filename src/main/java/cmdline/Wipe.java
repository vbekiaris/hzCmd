package cmdline;

import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "wipe", description = "killall -9 java on all box;  rm -fr hzCmd home dir")
public class Wipe extends Command
{
    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.wipe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
