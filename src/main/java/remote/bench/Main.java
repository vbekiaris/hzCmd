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

        System.out.println(System.getProperty("user.dir"));


        String vendorObject = "Hz instance";
        BenchManager b = new BenchManager(vendorObject);

        b.loadClass("A", "remote.bench.TestBench");
        b.loadClass("B", "remote.bench.TestBench2");
        b.setThreadCount(".*.", 4);


        b.setBenchType("A", BenchType.Hdr, 0, false, "A-out");
        b.setBenchType("B", BenchType.Metrics, 0, false, "B-out");



        b.setField("A", "valueSize", "1001");

        //b.init("A");
        //b.warmup("A", 6);
        //b.bench("A", 11);
        //b.cleanUp("A");
    }

}
