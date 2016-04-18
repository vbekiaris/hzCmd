package remote.bench;

public class Main {

    /*
put@class=hz.imap.Put
put@keyDomain=100000000
put@valueSetSize=1
put@valueMinSize=1000
put@valueMaxSize=1000
put@name=bakSync-1_onHeapEvict,bak-0_onHeapEvict
put@threads=4,16

get@class=hz.imap.Get
get@keyDomain=100000000
get@valueSetSize=1
get@valueMinSize=1000
get@valueMaxSize=1000
get@name=bakSync-1_onHeapEvict,bak-0_onHeapEvict
put@threads=2,8
    */

    public static void main(String[] args) throws Exception{

        String vendorObject = "Hz instance";
        BenchManager b = new BenchManager(vendorObject);

        b.setBenchType(BenchType.Metrics);
        b.setBenchClassName("remote.bench.TestBench");
        b.setThreadCount(4);
        b.setField("valueSize", "1001");
        b.setOutputFileName("0-put");

        b.init();
        b.warmup(6);
        b.bench(11);
        b.cleanUp();

    }
}
