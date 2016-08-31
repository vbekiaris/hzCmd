package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "bounce", description = "bounce jvm's in cluster")
public class Bounce extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    @Option(name = "-id", description = "default .*")
    public String clusterId=".*";

    @Option(name = "-iterations", description = "number of iteration default 1")
    public int iterations=1;

    @Option(name = "-restartDelay", description = "restart delay second default 10")
    public int restartDelay=10;

    @Option(name = "-iterationDelay", description = "next iteration delay second default 30")
    public int iterationDelay=0;

    @Option(name = "-v", description = "new vendor object version to start jvm at, default same version")
    public String version;

    @Option(name = "-ee", description = "start ee version, default false")
    public boolean ee=false;


    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.bounce(clusterId, ".*", iterations, restartDelay, iterationDelay, version, ee);
            }else{
                for (String id : ids) {
                    hzCmd.bounce(clusterId, id, iterations, restartDelay, iterationDelay, version, ee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
