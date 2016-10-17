package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "ls", description = "ls the cwd of jvm in cluster")
public class Ls extends Command
{
    @Option(name = "-id", description = "cluster id")
    public String id=".*";

    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.ls(id, ".*");
            }else{
                for (String jvmids : ids) {
                    hzCmd.ls(id, jvmids);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
