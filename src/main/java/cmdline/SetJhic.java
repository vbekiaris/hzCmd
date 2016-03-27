package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import local.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

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