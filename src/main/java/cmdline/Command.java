package cmdline;

import local.HzCmd;

public class Command implements Runnable {
    public void run() {
        System.out.println(getClass().getSimpleName());
    }

    public void exe(HzCmd hzCmd) {
        System.out.println(hzCmd);
    }
}
