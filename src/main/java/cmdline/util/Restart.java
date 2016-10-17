package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "restart", description = "restart a jvm in a cluster using its id")
public class Restart extends Command
{
    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Option(name = "-ee", description = "ee version")
    public boolean ee=false;

    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;


    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.restart(id, ".*", version, ee);
            }else{
                for (String jvmids : ids) {
                    hzCmd.restart(id, jvmids, version, ee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
