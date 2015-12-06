package remote;

import global.HzType;

import java.io.IOException;

/**
 * Created by danny on 8/29/15.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {

        Controler c = new Controler(HzType.Client);
        c.run();
    }
}