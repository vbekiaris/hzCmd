package remote;

import global.HzType;

import java.io.FileNotFoundException;

import static global.Utils.exceptionStacktraceToString;
import static remote.Utils.sendBackError;

public class Member {

    public static void main(String[] args) throws Throwable {
        Controler c = new Controler(HzType.Member);
        c.run();
    }
}
