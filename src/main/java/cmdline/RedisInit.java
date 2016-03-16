package cmdline;

import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;
import vendor.redis.RedisJvmFactory;

@com.github.rvesse.airline.annotations.Command(name = "init", description = "init redis props")
public class RedisInit extends Command {

    @Option(name = "-replicasCount", description = "default 1")
    public int replicasCount = 1;

    public void exe(HzCmd hzCmd) {
        RedisJvmFactory.replicasCount = replicasCount;
    }
}