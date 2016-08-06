package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "check", description = "check remote jvm for error, returns exit code 1 at error")
public class Check extends Command
{
    @Arguments(description = "jvm id default .*")
    public List<String> ids;

    public void exe(HzCmd hzCmd) {
        try {
            boolean errorFound=false;
            if(ids==null){
                errorFound = hzCmd.printErrors(".*");
            }else{
                for (String id : ids) {
                    errorFound = hzCmd.printErrors(id);
                }
            }
            if(errorFound){
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
