package global;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public abstract class Utils {


    public static final Escaper SHELL_ESCAPE;
    static {
        final Escapers.Builder builder = Escapers.builder();
        builder.addEscape('(', " ");
        builder.addEscape(')', " ");
        builder.addEscape('<', " ");
        builder.addEscape('>', " ");
        builder.addEscape('"', "\"");
        builder.addEscape('"', "\"");
        SHELL_ESCAPE = builder.build();
    }

    public static final Escaper QUOTES;
    static {
        final Escapers.Builder builder = Escapers.builder();
        builder.addEscape('"', "\"");
        builder.addEscape('\'', "\'");
        QUOTES = builder.build();
    }


    public static void sleepMilli(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepSeconds(int sec){
        sleepMilli(sec * 1000);
    }


    public static String escapeQuotes(String s) {
        return QUOTES.escape( s );
    }

    public static String exceptionStacktraceToString(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);


        return SHELL_ESCAPE.escape( sw.toString() );
    }


    public static int rangeMap(int val, int min_inclusive, int max_exclusive) {
        int p = max_exclusive-min_inclusive;
        int mod = (val-min_inclusive)%p;
        if(mod<0)
            mod += p;
        return min_inclusive+mod;
    }

}
