package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;

@com.github.rvesse.airline.annotations.Command(name = "jfr", description = "turn on jfr")
public class Jfr extends Command
{

    public void exe(HzCmd hzCmd) {
        try {
            HzCmdProperties properties = new HzCmdProperties();
            properties.writePropertie(HzCmdProperties.JFR, "true");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
