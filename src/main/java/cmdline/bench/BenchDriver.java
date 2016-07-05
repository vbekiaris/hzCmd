package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "driver", description = "set which jvm's run the bench, comma delimited list jvmId's")
public class BenchDriver extends Command {

    @Arguments(description = "comma delimited list of benchMark drivers")
    public List<String> drivers;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_DRIVER, commarDilinate(drivers));
    }
}
