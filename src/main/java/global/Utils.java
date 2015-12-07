package global;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import local.IpPair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public abstract class Utils {


    public static final Escaper SHELL_ESCAPE;
    static {
        final Escapers.Builder builder = Escapers.builder();
        builder.addEscape('(', " ");
        builder.addEscape(')', " ");
        SHELL_ESCAPE = builder.build();
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


    public static String exceptionStacktraceToString(Throwable e) {
        return SHELL_ESCAPE.escape( e +" "+Arrays.toString(e.getStackTrace()) );
    }


    public static int rangeMap(int val, int min_inclusive, int max_exclusive) {
        int p = max_exclusive-min_inclusive;
        int mod = (val-min_inclusive)%p;
        if(mod<0)
            mod += p;
        return min_inclusive+mod;
    }

}
