package remote.bench;

import remote.BenchType;

public class Main {

    public static void main(String[] args) throws Exception{

        BenchManager b = new BenchManager();

        b.setBenchType(BenchType.Metrics);
        b.setBenchClassName("remote.bench.TestBench");
        b.setThreadCount(4);
        b.setField("valueSize", "1001");

        b.init();
        b.warmup(10);
        b.bench(10);
        b.cleanUp();

    }
}
