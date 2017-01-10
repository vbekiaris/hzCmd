package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "flame", description = "flame graph")
public class Flame extends Command
{
    @Option(name = "-sec", description = "seconds")
    public int sec=120;

    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.flame(id, ".*", sec);
            }else{
                for (String jvmId : ids) {
                    hzCmd.flame(id, jvmId, sec);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
