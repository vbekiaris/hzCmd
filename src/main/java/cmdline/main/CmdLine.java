package cmdline.main;

import cmdline.bench.*;
import cmdline.chart.*;
import cmdline.redis.Redis;
import cmdline.redis.RedisInit;
import cmdline.set.*;
import cmdline.unix.*;
import cmdline.util.*;
import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;

@SuppressWarnings("unchecked")
public class CmdLine {

    @SuppressWarnings("unchecked")
    public static com.github.rvesse.airline.Cli<Runnable> getParser(){

        CliBuilder builder = new CliBuilder("hz");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(
                        Help.class,
                        Jfr.class,

                        SetMemberJvmOps.class, SetClientJvmOps.class, SetJhic.class, SetJhicArgs.class,
                        ClusterInit.class,

                        BenchDriver.class, BenchThreads.class, BenchDuration.class, BenchWarmup.class,
                        BenchTypes.class, BenchRun.class, BenchSubmit.class, BenchStop.class, BenchInterval.class, BenchThrow.class,

                        Check.class, Wipe.class, Clean.class, Histo.class, Hprof.class, MemberCount.class, RandMember.class, Flame.class,

                        Ls.class, Tail.class, Bash.class,
                        Kill.class, Exit.class, ReLaunch.class, Restart.class, RestartEmbedded.class, Bounce.class, Freeze.class,

                        Download.class, UploadLib.class, Rand.class,

                        Chart.class, ProcessJhicOutput.class, RegressionCheck.class,

                        Broker.class,

                        Redis.class,

                        Wan.class, Ip.class
                        );

        builder.withGroup("chart")
                .withDescription("chart bench data gnuplot install required")
                .withDefaultCommand(Help.class)
                .withCommands(ChartMetrics.class, ChartCompare.class);

        builder.withGroup("redis")
                .withDescription("init redis props")
                .withDefaultCommand(Help.class)
                .withCommands(RedisInit.class);

        return builder.build();
    }
}
