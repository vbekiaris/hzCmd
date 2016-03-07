package cmdline;

import com.github.rvesse.airline.builder.CliBuilder;
import com.github.rvesse.airline.help.Help;

@SuppressWarnings("unchecked")
public class CmdLine {


    /*
    * hzCmd init cluster -id A -size small -type HZ -boxes agent.txt -uplib ${benchJar} -upcwd ${trust} -version 3.6 "jvmOps"
    *
    * hzCmd bench driver HZClient,HZMember,
    *
    * hzCmd bench threads 8,32,64
    *
    * hzCmd bench durationSec 30
    *
    * hzCmd bench warmupSec 30
    *
    * hzCmd bench type METRICS
    *
    * hzCmd bench run -clusterid HZ bench.properties -out output/dir/
    *

    *
    * putBench@class.name.of.PutBench
    * putBench@keyDomain=1000
    * putBench@maxSize=3000
    * putBench@valueSize=1,2002,600000
    * putBench@mapName="asyncMap,offHeapMap"
    *
    * setBench@class.name.of.SetBench
    * setBench@keyDomain=1000
    * setBench@maxSize=3000
    * setBench@valueSize=1,2002,600000
    * setBench@mapName="asyncMap,offHeapMap"
    *
    *
    * hz.3.6_m4c32_putBench_benchClassName_drivers-m4c32_duration-30_TitleComment_threads-8_valueSize-1_mapName-asyncMap_99tile.png
    *
    * Gg.1.5.0-final_m4c32_putBench_benchClassName_drivers-m4c32_duration-30_TitleComment_threads-8_valueSize-1_mapName-asyncMap_99tile.png
    *
    *
    * class name in title ?
    *
    *
    *
    * */

    @SuppressWarnings("unchecked")
    public static com.github.rvesse.airline.Cli<Runnable> getParser(){

        CliBuilder builder = new CliBuilder("hzCmd");

        builder.withDescription("Hazelcast cluster cmd line control")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Info.class, Add.class, Set.class, MemberBox.class, Install.class,
                              Kill.class, Restart.class, Cat.class, Tail.class, Grep.class, Download.class,
                              Clean.class, Wipe.class, Load.class, Async.class, Sync.class, Exit.class, Listen.class,
                              Bench.class, Ssh.class, Ping.class, scpUp.class, UploadCwd.class, UploadLib.class, Broker.class,
                              Ls.class, Chart.class);

        builder.withGroup("add")
                .withDescription("add boxes, clusters, members clients")
                .withDefaultCommand(Help.class)
                .withCommands(AddBox.class, AddCluster.class, AddMember.class, AddClient.class);

        builder.withGroup("bench")
                .withDescription("benchmarking tools")
                .withDefaultCommand(Help.class)
                .withCommands(BenchRun.class);

        builder.withGroup("chart")
                .withDescription("chart bench data gnuplot install required")
                .withDefaultCommand(Help.class)
                .withCommands(ChartMetrics.class, ChartMetricsCompair.class);


        return builder.build();
    }
}
