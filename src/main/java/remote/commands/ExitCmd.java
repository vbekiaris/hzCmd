package remote.commands;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class ExitCmd implements Cmd, Serializable{
    public void exicute(){
        System.exit(0);
    }
}
