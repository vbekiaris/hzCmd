package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "embeddedRestart", description = "restart embedded vendor object")
public class RestartEmbedded extends Command
{

    @Option(name = "-id", description = "cluster id")
    public String id=".*";


    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.restartEmbeddedObject(id, ".*");
            }else{
                for (String jvmId : ids) {
                    hzCmd.restartEmbeddedObject(id, jvmId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
