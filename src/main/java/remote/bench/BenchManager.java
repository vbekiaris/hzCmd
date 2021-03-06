package remote.bench;

import com.google.gson.Gson;
import global.BenchType;
import global.ReplyMsg;
import mq.MQ;
import remote.main.Controler;
import remote.main.Utils;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class BenchManager {

    private static final Gson gson = new Gson();
    private Object vendorObject;
    private Map<String, BenchContainer> benchs = new HashMap();

    public BenchManager(Object vendorObject){
        this.vendorObject=vendorObject;
    }

    public void loadClass(MessageProducer replyProducer, String benchId, String clazz) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        msg.benchId=benchId;
        msg.benchClazz=clazz;
        try {
            BenchContainer benchContainer = new BenchContainer(vendorObject, benchId, clazz);
            benchs.put(benchId, benchContainer);
            msg.msg="Loaded";
            MQ.sendReply(replyProducer, gson.toJson(msg));
        } catch (Exception e) {
            Utils.recordeException(e);
            msg.error=true;
            msg.msg=e.toString();
            MQ.sendReply(replyProducer, gson.toJson(msg));
        }
    }

    public void removeBench(MessageProducer replyProducer, String id) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        try {
            BenchContainer removed = benchs.remove(id);
            msg.benchId=removed.getId();
            msg.msg="Removed";
            MQ.sendReply(replyProducer, gson.toJson(msg));
        } catch (Exception e) {
            Utils.recordeException(e);
            msg.error=true;
            msg.msg=e.toString();
            MQ.sendReply(replyProducer, gson.toJson(msg));
        }
    }

    public void setThreadCount(MessageProducer replyProducer, String id, int threadCount) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg="threadCount="+threadCount;
            try {
                benchContainer.createBenchObjects(threadCount);
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void writeMetaData(MessageProducer replyProducer, String id, String metaData) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg="meta data set";
            try {
                benchContainer.writeMetaData(metaData);
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void setField(MessageProducer replyProducer, String id, String field, String value) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg=field + " " + value;
            try {
                benchContainer.setField(field, value);
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void init(MessageProducer replyProducer, String id){
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg="init end";
            try {
                benchContainer.init();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void postPhase(MessageProducer replyProducer, String id){
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg="postPhase end";
            try {
                benchContainer.postPhase();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void setBenchType(MessageProducer replyProducer, String id, BenchType type, long expectedIntervalNanos, boolean recordException, String outFile){
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            msg.benchId=benchContainer.getId();
            msg.msg="BenchType="+type+" intervalNanos="+expectedIntervalNanos+" recordException="+recordException+" outFile="+outFile;
            try {
                benchContainer.setBenchType(type, expectedIntervalNanos, recordException, outFile);
                MQ.sendReply(replyProducer, gson.toJson(msg));
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
                MQ.sendReply(replyProducer, gson.toJson(msg));
            }
        }
    }

    public void setRunning(MessageProducer replyProducer, String benchId, boolean running) {
        ReplyMsg msg = new ReplyMsg();
        msg.id=Controler.ID;
        for (BenchContainer benchContainer : getMatchingBenchContainers(benchId)) {
            msg.benchId=benchContainer.getId();
            msg.msg=benchId+" running="+running;
            try {
                benchContainer.setRunning(running);
            } catch (Exception e) {
                Utils.recordeException(e);
                msg.error=true;
                msg.msg=e.toString();
            }
        }
    }

    public void warmup(MessageProducer replyProducer, String id, long seconds) {
        run(replyProducer, id, seconds, "warmup");
    }

    public void bench(MessageProducer replyProducer, String id, long seconds) {
        run(replyProducer, id, seconds, "bench");
    }

    private void run(MessageProducer replyProducer, String id, long seconds, String fileNamePostFix){

        List<BenchThread> threads = new ArrayList();
        Map<String, Integer> benchResults = new HashMap();

        //but the command to run the bench and its duration is the same.
        //i should set the bench warmup duration and runnning duration before hand.
        //this i can start all benchmarts at 1 time, with ID as star.
        //then i can set the time out on the other side at the max of the durations.


        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.preBench(fileNamePostFix);
            benchContainer.setDurationSeconds(seconds);
            threads.addAll( benchContainer.getThreads() );
            benchResults.put(benchContainer.getId(), benchContainer.getThreadCount());
        }

        for (BenchThread thread : threads) {
            thread.setReplyProducer(replyProducer);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(threads.size());
        CompletionService<ReplyMsg> service = new ExecutorCompletionService(threadPool);

        for (BenchThread thread : threads) {
            service.submit(thread);
        }
        threadPool.shutdown();

        //we send a reply for EACH thread IF its's and Exception
        //else we count the number of thread which have eneded
        //and for each all thread of a benchID, and send 1 reply.
        for (int i = 0; i < threads.size(); i++) {
            try {
                Future<ReplyMsg> future = service.take();
                ReplyMsg result = future.get();

                if (result.error) {
                    MQ.sendReply(replyProducer, gson.toJson(result));
                }
                else {
                    int count = benchResults.get(result.benchId) - 1;
                    benchResults.put(result.benchId, count);
                    if (count==0){
                        result.msg=fileNamePostFix+" End";
                        MQ.sendReply(replyProducer, gson.toJson(result));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.postBench();
        }
    }

    public void submitBench(String id, long seconds) {
        submit(id, seconds, "bench");
    }

    private void submit(String id, long seconds, String fileNamePostFix){

        List<BenchThread> threads = new ArrayList();
        Map<String, Integer> benchResults = new HashMap();

        for (BenchContainer benchContainer : getMatchingBenchContainers(id)) {
            benchContainer.preBench(fileNamePostFix);
            benchContainer.setDurationSeconds(seconds);
            threads.addAll( benchContainer.getThreads() );
            benchResults.put(benchContainer.getId(), benchContainer.getThreadCount());
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(threads.size());
        CompletionService<ReplyMsg> service = new ExecutorCompletionService(threadPool);

        for (BenchThread thread : threads) {
            service.submit(thread);
        }
        threadPool.shutdown();

        for (int i = 0; i < threads.size(); i++) {
            try {
                Future<ReplyMsg> future = service.take();
                future.get();
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
