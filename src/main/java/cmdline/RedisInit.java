package cmdline;

import com.github.rvesse.airline.annotations.Option;
import local.HzCmdProperties;
import main.HzCmd;
import vendor.redis.RedisJvmFactory;

import java.io.IOException;

@com.github.rvesse.airline.annotations.Command(name = "init", description = "init redis props")
public class RedisInit extends Command {

    @Option(name = "-replicasCount", description = "default 1")
    public String replicasCount = "1";

    public void exe(HzCmd hzCmd) throws IOException {

        HzCmdProperties p = new HzCmdProperties();
        p.writePropertie(HzCmdProperties.redisReplicas, replicasCount);
    }
}