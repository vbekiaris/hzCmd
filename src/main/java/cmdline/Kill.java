package cmdline;

import com.github.rvesse.airline.annotations.Option;

@com.github.rvesse.airline.annotations.Command(name = "kill", description = "kill -9 cluster/members/clients")
public class Kill extends Command
{
    @Option(name = "-cluster", description = "cluster id to kill, * for ALL")
    public String cluster;

    @Option(name = "-m", description = "member id to kill, * for ALL")
    public String member;

    @Option(name = "-c", description = "client id to kill, * for ALL")
    public String client;

    public void run() {

    }
}
