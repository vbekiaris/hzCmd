package cmdline.add;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.Bash;
import main.HzCmd;

import java.io.Serializable;
import java.util.List;

@com.github.rvesse.airline.annotations.Command(name = "member", description = "Add member jvm's to a cluster")
public class AddMember extends Command implements Serializable{

    @Option(name = "-cluster", description = "which cluster to add jvm's to. default *")
    public String cluster = ".*";

    @Option(name = "-n", description = "default number of members to add 0")
    public int qty = 0;

    @Option(name = "-v", description = "hazelcast version e.g. 3.6")
    public String version;

    @Option(name = "-up", description = "contigues list of file in a comma delimited string to upload to cwd of jvm")
    public String files;


    @Arguments(description = "jvm options")
    public List<String> jvmOptions;


    public void exe(HzCmd hzCmd) {
        System.out.println(Bash.ANSI_PURPLE +this + Bash.ANSI_RESET);
        try {
            StringBuilder cmd = new StringBuilder();
            for (String s : jvmOptions)
                cmd.append(s+" ");

            hzCmd.addMembers(cluster, qty, version, cmd.toString(), files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "AddMember{" +
                "cluster='" + cluster + '\'' +
                ", qty=" + qty +
                ", version='" + version + '\'' +
                ", jvmOptions='" + jvmOptions + '\'' +
                '}';
    }
}