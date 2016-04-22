package cmdline.util;

import cmdline.base.Command;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="listen", description = "listen for event about your workspace")
public class Listen extends Command {

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}