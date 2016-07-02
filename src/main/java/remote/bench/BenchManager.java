package remote.bench;

import global.BenchType;
import mq.MQ;
import remote.Controler;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class BenchManager {

    private Object vendorObject;
    private Map<String, BenchContainer> benchs = new HashMap();
    //private Map<String, ExecutorService> benchExecutors = new HashMap();


    public BenchManager(Object vendorObject){
        this.vendorObject=vendorObject;
    }


    public void loadClass(MessageProducer replyProducer, String benchId, String clazz) {
        try {
            BenchContainer benchContainer = new BenchContainer(vendorObject, benchId, clazz);
            benchs.put(benchId, benchContainer);
            MQ.sendReply(replyProducer, Controler.ID+" "+benchContainer.getId()+" "+clazz+" loaded");
        } catch (Exception e) {
            try {
                MQ.sendReply(replyProducer, e);
            } catch (JMSException e2) {}
        }
    }

    public void setThreadCount(MessageProducer replyProducer, String id, int threadCount) {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.createBenchObjects(threadCount);
                MQ.sendReply(replyProducer, Controler.ID+" "+benchContainer.getId()+" threadCount="+threadCount);
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void writeMetaData(MessageProducer replyProducer, String id, String metaData) {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.writeMetaData(metaData);
                MQ.sendReply(replyProducer, Controler.ID+" "+benchContainer.getId()+" metaData="+metaData);
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void setField(MessageProducer replyProducer, String id, String field, String value) {
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.setField(field, value);
                MQ.sendReply(replyProducer, Controler.ID+" " + benchContainer.getId() + " " + field + " " + value);
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void init(MessageProducer replyProducer, String id){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.init();
                MQ.sendReply(replyProducer, Controler.ID+ " " + benchContainer.getId() + " init end");
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void cleanUp(MessageProducer replyProducer, String id){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.cleanUp();
                MQ.sendReply(replyProducer, Controler.ID+" "+benchContainer.getId()+" cleanUp end");
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void setBenchType(MessageProducer replyProducer, String id, BenchType type, long expectedIntervalNanos, boolean allowException, String outFile){
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            try {
                benchContainer.setBenchType(type, expectedIntervalNanos, allowException, outFile);
                MQ.sendReply(replyProducer, Controler.ID+" "+benchContainer.getId()+" BenchType="+type+" intervalNanos="+expectedIntervalNanos+" allowException="+allowException+" outFile="+outFile);
            } catch (Exception e) {
                try {
                    MQ.sendReply(replyProducer, e);
                } catch (JMSException e2) {}
            }
        }
    }

    public void warmup(MessageProducer replyProducer, String id, int sec) {
        run(replyProducer, id, sec, "warmup");
    }

    public void bench(MessageProducer replyProducer, String id, int sec)   {
        run(replyProducer, id, sec, "bench");
    }

    private void run(MessageProducer replyProducer, String id, int sec, String fileNamePostFix){
        List<BenchRunThread> threads = new ArrayList();
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.preBench(fileNamePostFix);
            benchContainer.setDuration(sec);
            threads.addAll( benchContainer.getThreads() );
        }

        for (BenchRunThread thread : threads) {
            thread.setReplyProducer(replyProducer);
        }

        //ExecutorService exe = Executors.newFixedThreadPool(threads.size());
        //service.invokeAll(threads, sec + 600, TimeUnit.SECONDS);

        ExecutorService threadPool = Executors.newFixedThreadPool(threads.size());
        CompletionService<Object> service = new ExecutorCompletionService(threadPool);


        for (BenchRunThread thread : threads) {
            service.submit(thread);
        }
        threadPool.shutdown();
        for (BenchRunThread thread : threads) {
            try {
                Future<Object> future = service.take();
                Object result = future.get();

                System.out.println("HI got one+ " + result);
                if (result instanceof Exception) {
                    try {
                        MQ.sendReply(replyProducer, (Exception) result);
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
                if (result instanceof BenchRunThread) {
                    MQ.sendReply(replyProducer, Controler.ID+" "+result.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.postBench();
        }
    }


    private List<BenchContainer> getMatchingBenchContainers(String id) {
        List<BenchContainer> matching = new ArrayList();

        for(BenchContainer bench : benchs.values()){
            if ( bench.getId().matches(id) ){
                matching.add(bench);
            }
        }
        return matching;
    }
}
