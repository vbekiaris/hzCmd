package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import local.HzCmd;

import java.io.IOException;
import java.io.Serializable;

@com.github.rvesse.airline.annotations.Command(name = "member", description = "Add member jvm's to a cluster")
public class AddMember extends Command implements Serializable{

    @Option(name = "-cluster", description = "which cluster to add jvm's to. default *")
    public String cluster = "*";

    @Option(name = "-n", description = "default number of members to add 0")
    public int qty = 0;

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Arguments(description = "jvm options")
    public String jvmOptions;

    public void exe(HzCmd hzCmd) {
        try {
            hzCmd.addMembers(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}