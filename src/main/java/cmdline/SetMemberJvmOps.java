package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.Bash;
import local.HzCmdProperties;
import local.OrderedProperties;
import main.HzCmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

@com.github.rvesse.airline.annotations.Command(name = "memberOps", description = "Set jvm options for Members")
public class SetMemberJvmOps extends Command implements Serializable{

    @Arguments(description = "jvm options")
    public List<String> jvmOptions;


    public void exe(HzCmd hzCmd) {
        try {
            StringBuilder ops = new StringBuilder();
            for (String s : jvmOptions){
                ops.append(s+" ");
            }

            HzCmdProperties props = new HzCmdProperties();
            props.writePropertie(HzCmdProperties.memberOps, ops.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "AddMember{" +
                " jvmOptions='" + jvmOptions + '\'' +
                '}';
    }
}