package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class ExitCmd implements Cmd, Serializable{
    public void exicute(Controler c){
        System.exit(0);
    }
}
