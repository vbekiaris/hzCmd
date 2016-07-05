package cmdline.bench;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import local.properties.HzCmdProperties;
import main.HzCmd;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static global.Utils.commarDilinate;

@com.github.rvesse.airline.annotations.Command(name = "throw", description = "[true|false] default true")
public class BenchThrow extends Command implements Serializable{

    @Arguments(description = "throw Exception")
    public List<String> throwException;


    public void exe(HzCmd hzCmd) throws IOException {
        HzCmdProperties props = new HzCmdProperties();
        props.writePropertie(HzCmdProperties.BENCH_THROW, commarDilinate(throwException));
    }
}