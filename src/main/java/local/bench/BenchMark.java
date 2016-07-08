package local.bench;

import global.BenchType;
import local.properties.HzCmdProperties;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static global.Utils.cartesianProduct;

public class BenchMark implements Serializable {

    private HzCmdProperties properties = new HzCmdProperties();

    private Stack<List<FieldValue>> benchStack = new Stack<List<FieldValue>>();
    private List<FieldValue> currentBench;

    private int number=0;

    private String id;

    private String clazz;

    private List<FieldValue> drivers = new ArrayList<FieldValue>();

    private List<FieldValue> threads = new ArrayList<FieldValue>();

    private List<FieldValue> warmups = new ArrayList<FieldValue>();

    private List<FieldValue> durations = new ArrayList<FieldValue>();

    private List<FieldValue> benchTypes = new ArrayList<FieldValue>();

    private List<FieldValue> intervals = new ArrayList<FieldValue>();

    private List<FieldValue> throwException = new ArrayList<FieldValue>();

    private List<List<FieldValue>> attributes = new ArrayList<List<FieldValue>>();


    public BenchMark(String id){
        this.id=id;
    }

    public void addAttribute(FieldValue fieldValue) {

        if(fieldValue.field.equals("class")){
            clazz = fieldValue.values.get(0);
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_DRIVER)){
            drivers.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_THREADS)){
            threads.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_WARMUP)){
            warmups.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_DURATION)){
            durations.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_TYPE)){
            benchTypes.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_INTERVAL)){
            intervals.addAll(fieldValue.explode());
            return;
        }

        if(fieldValue.field.equals(HzCmdProperties.BENCH_THROW)){
            throwException.addAll(fieldValue.explode());
            return;
        }

        attributes.add(fieldValue.explode());
    }


    public void setupBenchMarkStack() {
        try {
            if (drivers.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_DRIVER, "Member");
                drivers.addAll(fieldValue.explode());
            }

            if (threads.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_THREADS, "4");
                threads.addAll(fieldValue.explode());
            }

            if (warmups.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_WARMUP, "30");
                warmups.addAll(fieldValue.explode());
            }

            if (durations.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_DURATION, "60");
                durations.addAll(fieldValue.explode());
            }

            if (benchTypes.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_TYPE, "Metrics");
                benchTypes.addAll(fieldValue.explode());
            }

            if (intervals.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_INTERVAL, "0");
                intervals.addAll(fieldValue.explode());
            }

            if (throwException.size()==0) {
                FieldValue fieldValue = properties.getFieldValue(HzCmdProperties.BENCH_THROW, "true");
                throwException.addAll(fieldValue.explode());
            }

        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }


        List<List<FieldValue>> list = new ArrayList<List<FieldValue>>();

        list.add(drivers);
        list.add(threads);
        list.add(warmups);
        list.add(durations);
        list.add(benchTypes);
        list.add(intervals);
        list.add(throwException);
        for (List<FieldValue> attributeList : attributes) {
            list.add(attributeList);
        }

        benchStack.addAll(cartesianProduct(list));
        for (Iterator<List<FieldValue>> iterator = benchStack.iterator(); iterator.hasNext();) {
            List<FieldValue> fieldValues = iterator.next();
            getRemoveField(fieldValues, HzCmdProperties.BENCH_DUMMEY);
        }
    }

    private FieldValue getRemoveField(List<FieldValue> list, String field){
        for (Iterator<FieldValue> iterator = list.iterator(); iterator.hasNext();) {
            FieldValue fieldValue = iterator.next();
            if (fieldValue.field.equals(field)) {
                iterator.remove();
                return fieldValue;
            }
        }
        return null;
    }

    private String getFieldValue(List<FieldValue> list, String field){
        for (Iterator<FieldValue> iterator = list.iterator(); iterator.hasNext();) {
            FieldValue fieldValue = iterator.next();
            if (fieldValue.field.equals(field)) {
                return fieldValue.values.get(0);
            }
        }
        return null;
    }

    public void popBenchMark() {
        if(benchStack.isEmpty()){
            return;
        }
        currentBench = benchStack.pop();
        number++;
    }

    public boolean isEmpty() {
        return benchStack.isEmpty();
    }

    public int benchStackCount(){
        return benchStack.size();
    }


    public List<FieldValue> getAttributes() {
        List<FieldValue> temp = new ArrayList();
        temp.addAll(currentBench);

        getRemoveField(temp, HzCmdProperties.BENCH_DRIVER);
        getRemoveField(temp, HzCmdProperties.BENCH_THROW);
        getRemoveField(temp, HzCmdProperties.BENCH_TYPE);
        getRemoveField(temp, HzCmdProperties.BENCH_THREADS);
        getRemoveField(temp, HzCmdProperties.BENCH_WARMUP);
        getRemoveField(temp, HzCmdProperties.BENCH_DURATION);
        getRemoveField(temp, HzCmdProperties.BENCH_INTERVAL);

        return temp;
    }

    public int getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }

    public String getClazz() {
        return clazz;
    }


    public String getDriver() {
        return getFieldValue(currentBench, HzCmdProperties.BENCH_DRIVER);
    }

    public boolean getThrowException() {
        return Boolean.parseBoolean( getFieldValue(currentBench, HzCmdProperties.BENCH_THROW) );
    }

    public BenchType getBenchType() {
        return BenchType.valueOf(getFieldValue(currentBench, HzCmdProperties.BENCH_TYPE));
    }


    public int getThreads() {
        return Integer.parseInt(getFieldValue(currentBench, HzCmdProperties.BENCH_THREADS));
    }

    public int getWarmup() {
        return Integer.parseInt(getFieldValue(currentBench, HzCmdProperties.BENCH_WARMUP) );
    }

    public int getDuration() {
        return Integer.parseInt(getFieldValue(currentBench, HzCmdProperties.BENCH_DURATION) );
    }

    public int getInterval() {
        return Integer.parseInt(getFieldValue(currentBench, HzCmdProperties.BENCH_INTERVAL) );
    }

    public String getMetaData(){
        return currentBench.toString();
    }



    @Override
    public String toString() {

        String stack = id+"@class="+clazz +"\n";
        for (List<FieldValue> strings : benchStack) {
            stack+= strings+"\n";
        }
        return stack.trim();
    }

}
