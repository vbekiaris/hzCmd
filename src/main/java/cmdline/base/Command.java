package cmdline.base;

import main.HzCmd;

import java.io.IOException;

@com.github.rvesse.airline.annotations.Command(name = "cmd")
public class Command implements Runnable {


    public void run() {
        System.out.println(getClass().getSimpleName());
    }

    public void exe(HzCmd hzCmd) throws IOException {

    }
}
