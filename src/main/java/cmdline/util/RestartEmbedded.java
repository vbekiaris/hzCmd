package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "embeddedRestart", description = "restart embedded vendor object")
public class RestartEmbedded extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.restartEmbeddedObject(".*");
            }else{
                for (String id : ids) {
                    hzCmd.restartEmbeddedObject(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
