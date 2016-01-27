package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class PingCmd implements Cmd, Serializable{

    public void exicute(Controler c){
        c.ping();
    }

    @Override
    public String toString() {
        return "PingCmd{}";
    }
}
