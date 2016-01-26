package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name="set", description = "group of commands,  you can set homip / show detailed info for ssh cmds /dedicated member Boxes")
public class Junked extends Command {

    @Option(name = "-homeip", description = "set the ip address of the home box (this)")
    public String homeip;

    @Option( name = "-showSSH",  description = "show ssh details on the cmd prompt")
    public boolean showSSH = false;


    public void exe(HzCmd hzCmd) {

        hzCmd.showSSH(showSSH);

        if (homeip != null){
            hzCmd.setHomeIp(homeip);
        }

    }

}