package cmdline;

import com.github.rvesse.airline.annotations.Option;

@com.github.rvesse.airline.annotations.Command(name = "jvm", description = "Add member/client jvm's to a cluster")
public class AddJvm extends Command {

    @Option(name = "-m", description = "default 0")
    public int member = 0;

    @Option(name = "-c", description = "default 0")
    public int client = 0;

    @Option(name = "-cluster", description = "default *")
    public String cluster = "*";

    @Option(name = "-v", description = "hazelcast version e.g. 9.7")
    public String version;

    @Option(name = "-o", description = "jvm options")
    public String options;

    public void run() {
        System.out.print(getClass().getSimpleName());
    }
}