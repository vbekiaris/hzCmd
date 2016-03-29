package cmdline;

import com.github.rvesse.airline.annotations.Option;
import global.Bash;
import main.HzCmd;

@com.github.rvesse.airline.annotations.Command(name = "create", description = "create aws instances")
public class AwsCreate extends Command {

    @Option(name = "-count", description = "instance count")
    String count;

    @Option(name = "-imageId", description = "ami")
    String imageId;

    @Option(name = "-region", description = "aws region")
    String region;

    @Option(name = "-type", description = "instance type")
    String type;

    @Option(name = "-key", description = "ssh key name in aws setup")
    String key;

    @Option(name = "-subnetId", description = "aws vpc / subnet id ")
    String subnetId;

    @Option(name = "-placementGroup", description = "aws placementGroup")
    String placementGroup;

    @Option(name = "-output", description = "output file name, listing public,private ip address of instances")
    String output;

    public void exe(HzCmd hzCmd) {
        try {

            Bash.executeCommand("aws-create "+count+" "+imageId+" "+region+" "+type+" "+key+" "+subnetId+" "+placementGroup+" "+output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}