package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "freeze", description = "freeze jvm's in cluster")
public class Freeze extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    @Option(name = "-id", description = "default .*")
    public String clusterId=".*";

    @Option(name = "-iterations", description = "number of iteration default 1")
    public int iterations=1;

    @Option(name = "-restartDelay", description = "unfreeze delay second default 10")
    public int restartDelay=10;

    @Option(name = "-iterationDelay", description = "next iteration delay second default 30")
    public int iterationDelay=0;


    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.freeze(clusterId, ".*", iterations, restartDelay, iterationDelay);
            }else{
                for (String id : ids) {

                    if(id.equals("RandomMember")) {
                        //hzCmd.freeze(clusterId, iterations, restartDelay, iterationDelay);
                    }else {
                        hzCmd.freeze(clusterId, id, iterations, restartDelay, iterationDelay);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
