package remote.bench;

class BenchThreadResult {

    public BenchThread benchThread=null;
    public Exception exception=null;

    public BenchThreadResult(BenchThread benchThread){
        this.benchThread=benchThread;
    }

    public BenchThreadResult(BenchThread benchThread, Exception exception){
        this.benchThread=benchThread;
        this.exception=exception;
    }

}