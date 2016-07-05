package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "threads", description = "comma delimited list of benchmark thread counts")
public class BenchThreads extends Command {

    @Arguments(description = "e.g. 8,16,32")
    public List<String> threads;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_THREADS, commarDilinate(threads));
    }
}
