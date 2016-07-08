package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "tail", description = "tail std out of jvm in cluster")
public class Tail extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.tail(".*");
            }else{
                for (String id : ids) {
                    hzCmd.tail(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
