package cmdline.set;

import cmdline.base.Command;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;

@com.github.rvesse.airline.annotations.Command(name = "JHIC", description = "enable JHIC")
public class SetJhic extends Command implements Serializable{


    public void exe(HzCmd hzCmd) {
        try {

            HzCmdProperties props = new HzCmdProperties();
            props.writePropertie(HzCmdProperties.JHIC, "true");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}