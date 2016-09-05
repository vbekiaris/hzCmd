package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "ls", description = "ls the cwd of jvm in cluster")
public class BenchStop extends Command
{
    @Option(name = "-id", description = "cluster's to run the stop cmd on, regex on cluster id ")
    public String clusterId=".*";

    @Option(name = "-jvmId", description = "cluster's to run the stop cmd on, regex on cluster id ")
    public String jvmId=".*";

    @Arguments(description = "benchmark ids")
    public List<String> benchIds;

    public void exe(HzCmd hzCmd) {

        try {
            if(benchIds==null){
                hzCmd.stopBench(clusterId, jvmId, ".*");
            }else{
                for (String id : benchIds) {
                    hzCmd.stopBench(clusterId, jvmId, id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
