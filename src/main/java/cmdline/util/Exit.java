package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "exit", description = "system.exit on jvm in cluster")
public class Exit extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.exit(".*");
            }else{
                for (String id : ids) {
                    hzCmd.exit(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
