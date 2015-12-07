package remote;

import global.HzType;

import java.io.FileNotFoundException;

public class Member {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Controler c = new Controler(HzType.Member);
        c.run();
    }
}
