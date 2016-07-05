package cmdline.set;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

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
            props.writePropertie(HzCmdProperties.MEMBER_OPS, ops.toString());

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