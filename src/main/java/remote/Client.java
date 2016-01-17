package remote;

import global.HzType;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(HzType.Client);
        c.run();
    }
}