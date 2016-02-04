package tests;

import remote.Controler;
import remote.TaskRunner;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by danny on 04/02/2016.
 */
public class ExTest {


    private ExecutorService executorService =  Executors.newCachedThreadPool();



    public void invokeAsync(int threadCount, Callable c) throws NoSuchMethodException {

        for (int i = 0; i <threadCount; i++) {
            executorService.submit(c);
        }
    }

    public void invokeSync(int threadCount, Callable c) throws NoSuchMethodException, InterruptedException {

        for (int i = 0; i <threadCount; i++) {
            executorService.submit(c);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("invokeSync END");
    }


    public static void main(String[] args) throws NoSuchMethodException, InterruptedException {
        ExTest t = new ExTest();

        t.invokeAsync(1, new LongTask());
        t.invokeSync(1, new ShortTask());

    }
}
