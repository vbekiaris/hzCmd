package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "load", description = "load a class")
public class Load extends Command
{
    @Option(name = "-id", description = "jvm id / name default to All jvm")
    public String jvmId=".*";

    @Option(name = "-task", description = "task Id")
    public String task;

    @Option(name = "-class", description = "client name")
    public String className;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.load(jvmId, task, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
