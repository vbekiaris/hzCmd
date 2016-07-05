package cmdline.set;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "JHIC_ARGS", description = "Set JHIC args only -d -i args expected ")
public class SetJhicArgs extends Command implements Serializable{

    @Arguments(description = "JHIC args default -d 0 -i 1000")
    public List<String> jhicArgs;


    public void exe(HzCmd hzCmd) {
        try {
            StringBuilder ops = new StringBuilder();
            for (String s : jhicArgs){
                ops.append(s+" ");
            }

            HzCmdProperties props = new HzCmdProperties();
            props.writePropertie(HzCmdProperties.JHIC_ARGS, ops.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}