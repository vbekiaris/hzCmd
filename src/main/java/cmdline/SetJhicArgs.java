package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import local.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "jhicArgs", description = "Set jhic args only -d -i args expected ")
public class SetJhicArgs extends Command implements Serializable{

    @Arguments(description = "jhic args default -d 0 -i 1000")
    public List<String> jhicArgs;


    public void exe(HzCmd hzCmd) {
        try {
            StringBuilder ops = new StringBuilder();
            for (String s : jhicArgs){
                ops.append(s+" ");
            }

            HzCmdProperties props = new HzCmdProperties();
            props.writePropertie(HzCmdProperties.jhicArgs, ops.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}