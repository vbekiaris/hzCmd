package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "ip", description = "print public ip of member")
public class Ip extends Command
{
    @Option(name = "-id", description = "cluster id default .*")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.ip(id, ".*");
            }else{
                for (String jvmids : ids) {
                    hzCmd.ip(id, jvmids);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
