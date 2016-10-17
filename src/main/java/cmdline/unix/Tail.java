package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "tail", description = "tail std out of jvm in cluster")
public class Tail extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.tail(id, ".*");
            }else{
                for (String jvmids : ids) {
                    hzCmd.tail(id, jvmids);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
