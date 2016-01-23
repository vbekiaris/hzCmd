package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "load", description = "load a class")
public class Load extends Command
{
    @Option(name = "-cluster", description = "cluster id, * for ALL")
    public String cluster;

    @Option(name = "-task", description = "task Id")
    public String task;

    @Option(name = "-class", description = "client name")
    public String className;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.load(cluster, task, className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
