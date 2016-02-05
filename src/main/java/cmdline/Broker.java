package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "broker", description = "broker ip address")
public class Broker extends Command
{
    @Option(name = "-ip", description = "ip address of broker, null for auto ip address of this")
    public String ip;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.setBrokerIP(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
