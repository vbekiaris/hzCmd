package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "ls", description = "ls the cwd of jvm in cluster")
public class Ls extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            if(ids==null){
                hzCmd.ls(".*");
            }else{
                for (String id : ids) {
                    hzCmd.ls(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
