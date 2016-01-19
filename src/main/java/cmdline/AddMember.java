package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.NodeType;
import local.HzCmd;

import java.io.IOException;
import java.io.Serializable;

@com.github.rvesse.airline.annotations.Command(name = "member", description = "Add member/client jvm's to a cluster")
public class AddMember extends Command implements Serializable{

    @Option(name = "-m", description = "default number of members to add 0")
    public int member = 0;

    @Option(name = "-c", description = "default number of clients to add 0")
    public int client = 0;

    @Option(name = "-cluster", description = "which cluster to add jvm's to. default *")
    public String cluster = "*";

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Arguments(description = "jvm options")
    public String options;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.addJvm(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}