package cmdline.set;

import cmdline.base.Command;
import local.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;

@com.github.rvesse.airline.annotations.Command(name = "jhic", description = "enable jhic")
public class SetJhic extends Command implements Serializable{


    public void exe(HzCmd hzCmd) {
        try {

            HzCmdProperties props = new HzCmdProperties();
            props.writePropertie(HzCmdProperties.jhic, "true");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}