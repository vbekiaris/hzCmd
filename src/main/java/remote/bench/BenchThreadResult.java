package remote.bench;

class BenchThreadResult {

    public BenchThread benchThread=null;
    public Throwable exception=null;

    public BenchThreadResult(BenchThread benchThread){
        this.benchThread=benchThread;
    }

    public BenchThreadResult(BenchThread benchThread, Throwable exception){
        this.benchThread=benchThread;
        this.exception=exception;
    }

}