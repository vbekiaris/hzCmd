package local;

import global.Bash;
import global.ClusterType;
import global.Utils;
import local.bench.BenchManager;
import local.bench.BenchMark;
import local.bench.FieldValue;
import vendor.coherence.CoherenceJvmFactory;
import vendor.gem.GemJvmFactory;
import vendor.gg.GgJvmFactory;
import vendor.hz.HzJvmFactory;
import vendor.redis.RedisJvmFactory;

import javax.jms.JMSException;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static global.Utils.myIp;


public class ClusterManager implements Serializable {

    private int TWO_MIN_AS_SECONDS = 120;

    private Map<String, ClusterContainer> clusters = new HashMap();

    public ClusterManager() { }

    public ClusterContainer initCluster(String id, ClusterType type, String brokerIP) throws Exception{

        ClusterContainer cluster = clusters.get(id);

        if(cluster==null) {
            if (brokerIP == null) {
                brokerIP = myIp();
            }
            switch (type) {
                case HZ:
                    cluster = new ClusterContainer(id, brokerIP, new HzJvmFactory());
                    break;
                case GG:
                    cluster = new ClusterContainer(id, brokerIP, new GgJvmFactory());
                    break;
                case GEM:
                    cluster = new ClusterContainer(id, brokerIP, new GemJvmFactory());
                    break;
                case RED:
                    cluster = new ClusterContainer(id, brokerIP, new RedisJvmFactory());
                    break;
                case CO:
                    cluster = new ClusterContainer(id, brokerIP, new CoherenceJvmFactory());
                    break;
            }
            clusters.put(cluster.getClusterId(), cluster);
        }
        return cluster;
    }

    private void getClusterResponses(List<ClusterContainer> clusters, BenchManager benchManager, long timeOut) throws InterruptedException, JMSException, IOException{
        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {

                cluster.getResponseExitOnException(benchMark.getDriver(), benchManager, timeOut);
            }
        }
    }

    public void loadBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        if(clusters.size()==0){
            System.out.println(Bash.ANSI_RED + "No Cluster selected "+id + Bash.ANSI_RESET);
            System.exit(1);
        }

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.load(benchMark.getDriver(), benchMark.getId(), benchMark.getClazz());
            }
        }

        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_2MIN);
    }

    public void setAttributes(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.setThreadCount(benchMark.getDriver(), benchMark.getId(), benchMark.getThreads());
            }
        }
        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_2MIN);


        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                String fileName = cluster.getClusterId() + "_" + cluster.getVersionsString() + "_" + benchMark.getId() + "_" + benchMark.getClazz() + "_" + benchMark.getNumber();
                cluster.setBenchType(benchMark.getDriver(), benchMark.getId(), benchMark.getBenchType(), benchMark.getInterval(), benchMark.getThrowException(), fileName);
            }
        }
        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_2MIN);


        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                for (FieldValue fieldValue : benchMark.getAttributes()) {
                    cluster.setField(benchMark.getDriver(), benchMark.getId(), fieldValue.field, fieldValue.values.get(0));
                }
            }
        }
        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                for (FieldValue fieldValue : benchMark.getAttributes()) {
                    cluster.getResponseExitOnException(benchMark.getDriver(), benchManager, Utils.TIMEOUT_2MIN);
                }
            }
        }
    }

    public void initBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.initBench(benchMark.getDriver(), benchMark.getId());
            }
        }
        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_10MIN);
    }

    public void warmupBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                if(benchMark.getWarmupSeconds()!=0){
                    cluster.warmupBench(benchMark.getDriver(), benchMark.getId(), benchMark.getWarmupSeconds());
                }
            }
        }
        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                if(benchMark.getWarmupSeconds()!=0){
                    long timout = TimeUnit.SECONDS.toMillis(benchMark.getWarmupSeconds() + TWO_MIN_AS_SECONDS);
                    cluster.getResponseExitOnException(benchMark.getDriver(), benchManager, timout);
                }
            }
        }
    }

    public void runBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.runBench(benchMark.getDriver(), benchMark.getId(), benchMark.getDurationSeconds());
            }
        }
        long timout = TimeUnit.SECONDS.toMillis(benchManager.getMaxDurationSeconds() + TWO_MIN_AS_SECONDS);
        getClusterResponses(clusters, benchManager, timout);
    }


    public void submitBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.submitBench(benchMark.getDriver(), benchMark.getId(), benchMark.getDurationSeconds());
            }
        }
        getClusterResponses(clusters, benchManager, TWO_MIN_AS_SECONDS);
    }


    public void postPhaseBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.postPhaseBench(benchMark.getDriver(), benchMark.getId());
            }
        }
        getClusterResponses(clusters, benchManager,  Utils.TIMEOUT_5MIN);
    }

    public void writeMetaDataCmd(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {
        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {

                String info = cluster.getClusterId()+" "+"M"+cluster.getMemberCount()+"C"+cluster.getClientCount()+" "+cluster.getVersionsString()+" "+benchMark.getId()+" "+benchMark.getClazz()+" "+benchMark.getNumber();
                String meta = benchMark.getMetaData();

                cluster.writeMetaDataCmd(benchMark.getDriver(), benchMark.getId(), info+" "+meta);
            }
        }
        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_2MIN);
    }

    public void removeBench(String id, BenchManager benchManager) throws InterruptedException, JMSException, IOException {

        List<ClusterContainer> clusters = getClusters(id);

        for (ClusterContainer cluster : clusters) {
            for (BenchMark benchMark : benchManager.getBenchMarks()) {
                cluster.removeBench(benchMark.getDriver(), benchMark.getId());
            }
        }

        getClusterResponses(clusters, benchManager, Utils.TIMEOUT_2MIN);
    }


    public List<ClusterContainer> getClusters(String id) {
        List<ClusterContainer> matching = new ArrayList();

        for(ClusterContainer c : clusters.values()){
            if ( c.getClusterId().matches(".*" + id + ".*") ){
                matching.add(c);
            }
        }

        if(matching.size()==0){
            System.out.println(Bash.ANSI_RED+"Zero clusters matching regex "+".*"+id+".*" +Bash.ANSI_RESET);
            System.exit(1);
        }

        return matching;
    }

    public Collection<ClusterContainer> getClusters() {
        return clusters.values();
    }

}