package cmdline;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.Bash;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "client", description = "Add client jvm's to a cluster")
public class AddClient extends Command implements Serializable{

    @Option(name = "-cluster", description = "which cluster to add jvm's to. default .*")
    public String cluster = ".*";

    @Option(name = "-n", description = "default number of members to add 1")
    public int qty = 1;

    @Option(name = "-v", description = "hazelcast version e.g. 3.6")
    public String version;

    @Arguments(description = "jvm options")
    public List<String> jvmOptions;


    public void exe(HzCmd hzCmd) {
        System.out.println(Bash.ANSI_PURPLE +this + Bash.ANSI_RESET);
        try {
            StringBuilder cmd = new StringBuilder();
            for (String s : jvmOptions)
                cmd.append(s+" ");

            hzCmd.addClients(cluster, qty, version, cmd.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "AddClient{" +
                "cluster='" + cluster + '\'' +
                ", qty=" + qty +
                ", version='" + version + '\'' +
                ", jvmOptions=" + jvmOptions +
                '}';
    }
}