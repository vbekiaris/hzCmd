package cmdline.main;

import cmdline.add.*;
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

        CliBuilder builder = new CliBuilder("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Info.class, Aws.class, Add.class, Set.class, MemberBox.class, Install.class,
                              Kill.class, Restart.class, Jps.class, Cat.class, Tail.class, Grep.class, Download.class,
                              Clean.class, Wipe.class, Load.class, Async.class, Sync.class, Exit.class, Listen.class,
                              Bench.class, Ssh.class, Ping.class, scpUp.class, UploadCwd.class, UploadLib.class, Broker.class,
                              Ls.class, Chart.class, Redis.class, ProcessJhicOutput.class, Bounce.class, RegressionCheck.class, Check.class);

        builder.withGroup("aws")
                .withDescription("aws create ec2 instances needs aws ec2 cmd line tools installed")
                .withDefaultCommand(Help.class)
                .withCommands(AwsCreate.class);

        builder.withGroup("init")
                .withDescription("init a cluster ")
                .withDefaultCommand(Help.class)
                .withCommands(InitCluster.class);

        builder.withGroup("set")
                .withDescription("set properties")
                .withDefaultCommand(Help.class)
                .withCommands(SetMemberJvmOps.class, SetClientJvmOps.class, SetJhic.class, SetJhicArgs.class);

        builder.withGroup("add")
                .withDescription("add boxes, clusters, members clients")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddCluster.class, AddMember.class, AddClient.class);

        builder.withGroup("bench")
                .withDescription("benchmarking tools")
                .withDefaultCommand(Help.class)
                .withCommands(BenchDriver.class, BenchThreads.class, BenchDuration.class, BenchWarmup.class,
                              BenchTypes.class, BenchRun.class, BenchInterval.class, BenchRepeat.class, BenchAllowException.class);

        builder.withGroup("chart")
                .withDescription("chart bench data gnuplot install required")
                .withDefaultCommand(Help.class)
                .withCommands(ChartMetrics.class, ChartCompare.class, ChartMetricsCompair.class, ChartHdrCompair.class);

        builder.withGroup("redis")
                .withDescription("init redis props")
                .withDefaultCommand(Help.class)
                .withCommands(RedisInit.class);

        return builder.build();
    }
}
