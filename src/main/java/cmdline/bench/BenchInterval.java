package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "interval", description = "set expected interval of ops in millis")
public class BenchInterval extends Command {

    @Arguments(description = "default max rate 1 operation per millisecond")
    public List<String> interval;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_INTERVAL, commarDilinate(interval));
    }
}
