package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "hprof", description = "jmap hprof")
public class Hprof extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.jmapHprof(id, ".*");
            }else{
                for (String jvmId : ids) {
                    hzCmd.jmapHprof(id, jvmId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
