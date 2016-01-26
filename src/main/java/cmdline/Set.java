package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="set", description = "set public member variables of a taskId in some jvms")
public class Set extends Command {

    @Option(name = "-id", description = "jvm id / name")
    public String jvmId=".*";

    @Option(name = "-task", description = "task id")
    public String taskId;

    @Option(name = "-var", description = "public member field to set")
    public String var;

    @Option(name = "-val", description = "value to be set")
    public String value;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setField(jvmId, taskId, var, var);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}