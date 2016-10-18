package global;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Utils {

    public static final long TIMEOUT_2MIN = TimeUnit.SECONDS.toMillis(120);
    public static final long TIMEOUT_5MIN = TimeUnit.SECONDS.toMillis(300);
    public static final long TIMEOUT_10MIN = TimeUnit.SECONDS.toMillis(600);
    public static final long TIMEOUT_45MIN = TimeUnit.SECONDS.toMillis(2700);

    public static String myIp() {

        URL whatismyip = null;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        } catch (IOException e) {
            System.out.println(Bash.ANSI_RED + "error connecting to http://checkip.amazonaws.com 'hz help broker' " + Bash.ANSI_RESET);
            System.exit(1);
        }
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
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
        return QUOTES.escape(s);
    }


    public static int rangeMap(int val, int min_inclusive, int max_exclusive) {
        int p = max_exclusive - min_inclusive;
        int mod = (val - min_inclusive) % p;
        if (mod < 0)
            mod += p;
        return min_inclusive + mod;
    }

    public static List<Integer> getIntList(List<String> numberStrings) {
        List<Integer> numbers = new ArrayList();
        for (String numberString : numberStrings) {
            numbers.add(Integer.parseInt(numberString));
        }
        return numbers;
    }

    public static List<BenchType> getBenchTypeList(List<String> benchTypes) {
        List<BenchType> list = new ArrayList();
        for (String t : benchTypes) {
            list.add(BenchType.valueOf(t));
        }
        return list;
    }

    public static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

    public static long timeStringToNanos(String timeString) {

        Pattern sec = Pattern.compile("[0-9]+s");
        Pattern mil = Pattern.compile("[0-9]+m");
        Pattern mic = Pattern.compile("[0-9]+u");
        Pattern nan = Pattern.compile("[0-9]+n");

        Matcher matcher = sec.matcher(timeString);
        if (matcher.find()) {
            timeString = timeString.replaceAll("s|m|u|n", "");
            return TimeUnit.SECONDS.toNanos(Long.parseLong(timeString));
        }

        matcher = mil.matcher(timeString);
        if (matcher.find()) {
            timeString = timeString.replaceAll("s|m|u|n", "");
            return TimeUnit.MILLISECONDS.toNanos(Long.parseLong(timeString));
        }

        matcher = mic.matcher(timeString);
        if (matcher.find()) {
            timeString = timeString.replaceAll("s|m|u|n", "");
            return TimeUnit.MICROSECONDS.toNanos(Long.parseLong(timeString));
        }

        matcher = nan.matcher(timeString);
        if (matcher.find()) {
            timeString = timeString.replaceAll("s|m|u|n", "");
            return Long.parseLong(timeString);
        }

        return TimeUnit.MILLISECONDS.toNanos(Long.parseLong(timeString));
    }


    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static String commarDilinate(List<String> strings) {
        String res = new String();
        for (String s : strings) {
            res += s + ",";
        }
        return removeLastChar(res);
    }


    public static void main(String[] args) {

        double valueSize = 1000;
        //double keyDomain = 100000000;
        double keyDomain = 50000000;

        double backups = 1;

        double clusterSize = 4;

        //double partitions = 271;
        double partitions = 2711;


        double totalDataGB = valueSize * keyDomain * (backups + 1) / 1073741824;

        double perMember = totalDataGB / clusterSize;

        double perPartition = totalDataGB / partitions;

        double keysPerPartition = keyDomain / partitions;

        System.out.println("GB data      =" + totalDataGB);
        System.out.println("GB per Member=" + perMember);
        System.out.println("GB per Partition=" + perPartition);


        System.out.println("keys per Partition=" + keysPerPartition);
    }
}