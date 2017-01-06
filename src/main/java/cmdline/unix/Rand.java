package cmdline.unix;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Option;
import main.HzCmd;

import java.util.Random;

import static global.Utils.rangeMap;

@com.github.rvesse.airline.annotations.Command(name = "rand", description = "random int between -min inclusive  -max inclusive")
public class Rand extends Command
{
    @Option(name = "-min", description = "min default 0")
    public int min=0;

    @Option(name = "-max", description = "max default 1")
    public int max=1;

    public void exe(HzCmd hzCmd) {

        Random random = new Random();

        System.out.println( rangeMap(random.nextInt(), min, max+1) );
    }
}
