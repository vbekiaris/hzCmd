package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "kill", description = "kill -9 jvm in cluster")
public class Kill extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.kill(id, ".*");
            }else{
                for (String jvmId : ids) {
                    hzCmd.kill(id, jvmId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
