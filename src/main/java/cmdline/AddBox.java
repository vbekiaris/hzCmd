package cmdline;

import com.github.rvesse.airline.annotations.Option;
import local.HzCmd;

import java.io.IOException;

@com.github.rvesse.airline.annotations.Command(name = "boxes", description = "file of ip address and ssh login user name")
public class AddBox extends Command {

    @Option(name = "-u", description = "default ec2-user")
    public String user = "admin";

    @Option(name = "-f", description = "default agents.txt")
    public String file = "agents.txt";

    public void run() {
        System.out.println(getClass().getSimpleName() + " " + user + " " + file);
    }

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.addBoxes(user, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}