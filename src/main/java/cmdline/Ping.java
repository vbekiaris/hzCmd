package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.HzType;
import local.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "ping", description = "ping a cluster's/member's/client's")
public class Ping extends Command
{
    @Option(name = "-cluster", description = "cluster id * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id * for ALL")
    public String member;

    @Option(name = "-c", description = "client id * for ALL")
    public String client;


    public void exe(HzCmd hzCmd) {

    }
}
