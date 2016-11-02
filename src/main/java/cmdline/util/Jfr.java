package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "jfr", description = "no, optional arguments")
public class Jfr extends Command
{

    @Arguments(description = "jfr args")
    public List<String> args;


    public void exe(HzCmd hzCmd) {
        try {
            HzCmdProperties properties = new HzCmdProperties();
            properties.writePropertie(HzCmdProperties.JFR, "true");

            if(args!=null){
                String argsStr = new String();
                for (String arg : args) {
                    argsStr +=arg+" ";
                }
                argsStr = argsStr.trim();
                properties.writePropertie(HzCmdProperties.JFR_ARGS, argsStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
