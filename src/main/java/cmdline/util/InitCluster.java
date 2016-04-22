package cmdline.util;

import cmdline.base.Command;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Option;
import global.ClusterSize;
import global.ClusterType;
import main.HzCmd;

import java.util.List;

@com.github.rvesse.airline.annotations.Command(name="cluster", description = "init a cluster")
public  class InitCluster extends Command {

    //hzCmd init cluster -id A -size s -type HZ -boxes agent.txt -uplib ${benchJar} -upcwd ${trust} -version 3.6

    @Option(name = "-user", description = "user name to access boxes (default ec2-user)")
    public String user="ec2-user";

    @Option(name = "-id", description = "set name of cluster")
    public String id;

    @Option(name = "-size", description = "set size of cluster (default S)")
    public String size="S";

    @Option(name = "-boxes", description = "name of boxes file (default agents.txt)")
    public String boxes = "agents.txt";

    @Option(name = "-type", description = "(default HZ), type of cluster [ClusterType.HZ | ClusterType.GG | ClusterType.GEM]" )
    public String type = "HZ";

    @Option(name = "-ee", description = "Hazelcast enterprise (default false)")
    public boolean ee = false;

    @Option(name = "-v", description = "hazelcast version e.g. 3.6")
    public String version;

    @Option(name = "-upcwd", description = "contigues list of file in a comma delimited string to upload to cwd of jvm")
    public String cwd_file=null;

    @Option(name = "-uplib", description = "contigues list of file in a comma delimited string to upload to lib of jvm before start")
    public String lib_files=null;

    @Arguments(description = "jvm options")
    public List<String> jvmOptions;

    public void exe(HzCmd hzCmd) {
        try {
            ClusterType clusterType = ClusterType.valueOf(type);
            ClusterSize clusterSize = ClusterSize.valueOf(size);

            hzCmd.initCluster(user, boxes, id, clusterType, clusterSize, ee, version, lib_files, cwd_file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
