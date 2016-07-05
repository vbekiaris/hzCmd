package cmdline.main;

import cmdline.aws.Aws;
import cmdline.aws.AwsCreate;
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
                        Aws.class,

                        SetMemberJvmOps.class, SetClientJvmOps.class, SetJhic.class, SetJhicArgs.class,
                        ClusterInit.class,

                        BenchDriver.class, BenchThreads.class, BenchDuration.class, BenchWarmup.class,
                        BenchTypes.class, BenchRun.class, BenchInterval.class, BenchThrow.class,

                        Check.class, Wipe.class, Clean.class,

                        Ls.class, Tail.class,
                        Kill.class, Exit.class, Restart.class, RestartEmbedded.class, Bounce.class,

                        Download.class, UploadLib.class,

                        Chart.class, ProcessJhicOutput.class, RegressionCheck.class,

                        Broker.class,

                        Redis.class
                        );

        builder.withGroup("aws")
                .withDescription("aws create ec2 instances needs aws ec2 cmd line tools installed")
                .withDefaultCommand(Help.class)
                .withCommands(AwsCreate.class);


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
