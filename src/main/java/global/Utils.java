package global;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;


import java.net.*;
import java.io.*;


public abstract class Utils {

    public static String myIp() throws Exception{
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader( whatismyip.openStream() ));
        return in.readLine();
    }


    public static final Escaper SHELL_ESCAPE;
    static {
        final Escapers.Builder builder = Escapers.builder();
        builder.addEscape('(', " ");
        builder.addEscape(')', " ");
        builder.addEscape('<', " ");
        builder.addEscape('>', " ");
        builder.addEscape('"', "\"");
        builder.addEscape('"', "\"");
        builder.addEscape(';', "\\;");
        SHELL_ESCAPE = builder.build();
    }

    public static final Escaper QUOTES;
    static {
        final Escapers.Builder builder = Escapers.builder();
        builder.addEscape('"', "\"");
        builder.addEscape('\'', "\'");
        QUOTES = builder.build();
    }

    public static String escapeQuotes(String s) {
        return QUOTES.escape( s );
    }


    public static int rangeMap(int val, int min_inclusive, int max_exclusive) {
        int p = max_exclusive-min_inclusive;
        int mod = (val-min_inclusive)%p;
        if(mod<0)
            mod += p;
        return min_inclusive+mod;
    }

    public static boolean printStringIfNotEmpty(String str, String color){
        if(str != null && !str.isEmpty()){
            System.out.println(color+str+Bash.ANSI_RESET);
            return true;
        }
        return false;
    }
}
