package tests;


import java.util.concurrent.Callable;


public class LongTask implements Callable{
    public Object call() throws Exception {
        System.out.println("Long Started");
        Thread.sleep(15000);
        System.out.println("Long end");
        return null;
    }
}