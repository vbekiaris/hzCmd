package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "marker", description = "type of benchmark tool used to measure")
public class BenchTypes extends Command {

    @Arguments(description = "[Metrics|Hdr|Graphite] default Metrics")
    public List<String> types;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_TYPE, commarDilinate(types));
    }
}
