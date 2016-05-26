package remote.command;

import remote.Controler;

import java.io.Serializable;

/**
 * Created by danny on 22/01/2016.
 */
public class RestartEmbeddedObjectCmd implements Cmd, Serializable{
    public void exicute(Controler c) throws Exception {
        c.startEmbeddedObject();
    }
}
