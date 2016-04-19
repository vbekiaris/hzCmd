package remote.command.bench;

import remote.Controler;
import remote.bench.BenchType;
import remote.command.Cmd;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class SetBenchTypeCmd implements Cmd, Serializable{

    private String taskId;
    private BenchType type;
    private long nanos;
    private boolean stop;
    private String outFile;

    public SetBenchTypeCmd(String taskId, BenchType type, long nanos, boolean stop, String outFile){
        this.taskId = taskId;
        this.type = type;
        this.nanos = nanos;
        this.stop = stop;
        this.outFile = outFile;
    }

    public void exicute(Controler c){
        c.setBenchType(taskId, type, nanos, stop, outFile);
    }

    @Override
    public String toString() {
        return "SetBenchTypeCmd{" +
                "nanos=" + nanos +
                ", taskId='" + taskId + '\'' +
                ", type=" + type +
                ", stop=" + stop +
                ", outFile='" + outFile + '\'' +
                '}';
    }
}
