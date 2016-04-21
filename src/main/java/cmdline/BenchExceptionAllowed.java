package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import main.HzCmd;

import java.io.Serializable;

@com.github.rvesse.airline.annotations.Command(name = "allowException", description = "[true|false] default (false)")
public class BenchExceptionAllowed extends Command implements Serializable{

    @Arguments(description = "allow bench marks to throw exception and continue calling timeStep [true|false]. default (false)")
    public String allow="false";


    public void exe(HzCmd hzCmd) {
        try {
            boolean allowBool = Boolean.parseBoolean(allow);
            hzCmd.setBenchAllowException(allowBool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}