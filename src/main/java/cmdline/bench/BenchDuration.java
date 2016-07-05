package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "bench", description = "benchmark duration seconds")
public class BenchDuration extends Command {

    @Arguments(description = "default 60")
    public List<String> duration;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_DURATION, commarDilinate(duration));
    }
}
