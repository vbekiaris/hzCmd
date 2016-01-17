package remote;

import global.HzType;

public class Member {
    public static void main(String[] args) throws Throwable {
        Controler c = new HzControler(HzType.Member);
        c.run();
    }
}
