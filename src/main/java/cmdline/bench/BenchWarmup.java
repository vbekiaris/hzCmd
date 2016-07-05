package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "warmup", description = "benchmark warmup duration seconds")
public class BenchWarmup extends Command {

    @Arguments(description = "default 30")
    public List<String> warmup;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_WARMUP, commarDilinate(warmup));
    }
}
