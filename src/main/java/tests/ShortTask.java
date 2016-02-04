package tests;


import java.util.concurrent.Callable;

public class ShortTask implements Callable {
    public Object call() throws Exception {
        System.out.println("Short Started");
        Thread.sleep(5000);
        System.out.println("Short end");
        return null;
    }
}
