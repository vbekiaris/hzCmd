package commands;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class StopCmd implements Cmd, Serializable{

    private String taskId;

    public void exicute(){

        tasks.stop(words[1]);

    }
}
