package cmdline;

import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;

@SuppressWarnings("unchecked")
public class CmdLine {

    @SuppressWarnings("unchecked")
    public static com.github.rvesse.airline.Cli<Runnable> getParser(){

        CliBuilder builder = new CliBuilder("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Info.class, Add.class, Set.class, MemberBox.class, Install.class,
                              Kill.class, Restart.class, Cat.class, Tail.class, Grep.class, Download.class,
                              Clean.class, Wipe.class, Load.class, Async.class, Sync.class, Exit.class, Listen.class,
                              Bench.class, Ssh.class, Ping.class, scpUp.class, UploadCwd.class, UploadLib.class, Broker.class,
                              Ls.class, Chart.class, Redis.class);

        builder.withGroup("init")
                .withDescription("init a cluster ")
                .withDefaultCommand(Help.class)
                .withCommands(InitCluster.class);

        builder.withGroup("set")
                .withDescription("set properties")
                .withDefaultCommand(Help.class)
                .withCommands(SetMemberJvmOps.class, SetClientJvmOps.class, SetJhic.class);

        builder.withGroup("add")
                .withDescription("add boxes, clusters, members clients")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddCluster.class, AddMember.class, AddClient.class);

        builder.withGroup("bench")
                .withDescription("benchmarking tools")
                .withDefaultCommand(Help.class)
                .withCommands(BenchDriver.class, BenchThreads.class, BenchDuration.class, BenchWarmup.class, BenchTypes.class, BenchRun.class, BenchRepeat.class);

        builder.withGroup("chart")
                .withDescription("chart bench data gnuplot install required")
                .withDefaultCommand(Help.class)
                .withCommands(ChartMetrics.class, ChartMetricsCompair.class, ChartHdrCompair.class);

        builder.withGroup("redis")
                .withDescription("init redis props")
                .withDefaultCommand(Help.class)
                .withCommands(RedisInit.class);

        return builder.build();
    }
}
