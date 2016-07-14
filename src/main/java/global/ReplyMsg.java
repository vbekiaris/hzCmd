package global;

public class ReplyMsg {

    public String id;
    public String benchId;
    public String threadId;
    public String benchClazz;
    public boolean error=false;
    public String msg;

    public String toString() {
        String color = error ? Bash.ANSI_RED : Bash.ANSI_GREEN;
        return color +
                (id==null ? "" : id+" ")+
                (benchId==null ? "" : benchId+" ")+
                (benchClazz==null ? "": benchClazz+" ")+
                (threadId==null ? "" : "threadId="+threadId+" ")+
                (msg==null ? "":msg+" ")+
                Bash.ANSI_RESET;
    }
}
