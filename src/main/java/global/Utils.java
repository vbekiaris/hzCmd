package global;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

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


    public static String exceptionStacktraceToString(Exception e) {
        return SHELL_ESCAPE.escape( e +" "+Arrays.toString(e.getStackTrace()) );
    }
}
