package local;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by danny on 03/03/2016.
 */
public class BenchManager {

    Multimap<String, FieldValue> benchMap;
    SortedSet<String> orderedTaskIds;


    public Set<String> getTaskIds(){
        return orderedTaskIds;
    }

    public String getClassName(String taskId){
        for (FieldValue fieldValue : benchMap.get(taskId)) {
            if(fieldValue.field.equals("class")){
                return fieldValue.value;
            }
        }
        return null;
    }

    public List<FieldValue> getIteratedFields(String taskId){
        List<FieldValue> f = new ArrayList();

        for (FieldValue fieldValue : benchMap.get(taskId)) {
            if(fieldValue.value.contains(",")) {
                f.add(fieldValue);
            }
        }
        return f;
    }

    public List<List<FieldValue>> getAllIteratedFields(String taskid){
        List<List<FieldValue>> all = new ArrayList();
        for (FieldValue fieldValue : getIteratedFields(taskid)) {

            List<FieldValue> list = new ArrayList();


            String field = fieldValue.field;
            List<String> values = fieldValue.getValues();

            for (String v : values) {
                list.add(new FieldValue(field, v));
            }
            all.add(list);
        }
        return all;
    }

    public List<FieldValue> getFieldsToSet(String taskId){
        List<FieldValue> f = new ArrayList();

        for (FieldValue fieldValue : benchMap.get(taskId)) {
            if( !fieldValue.field.equals("class") && !fieldValue.value.contains(",")) {
                f.add(fieldValue);
            }
        }
        return f;
    }

    public List<String> getClassesNameList(){
        List<String> calsses = new ArrayList();
        for (String k : orderedTaskIds) {
            calsses.add(getClassName(k));
        }
        return calsses;
    }

    public BenchManager(String file) throws InterruptedException, IOException {

        InputStream input=null;
        try {

            Properties prop = new OrderedProperties();
            input = new FileInputStream(file);
            prop.load(input);

            benchMap = ArrayListMultimap.create();
            orderedTaskIds = new TreeSet();

            Enumeration<?> enumeration = prop.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = prop.getProperty(key);

                String[] keyFile = key.split("@");

                key = keyFile[0];
                String feild = keyFile[1];

                FieldValue fv = new FieldValue(feild, value);

                benchMap.put(key, fv);
                orderedTaskIds.add(key);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<List<FieldValue>> getSettings(String taskid){
         return cartesianProduct( getAllIteratedFields(taskid) );
    }

    protected <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
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

    public static void main(String[] args) throws InterruptedException, IOException {

        BenchManager r = new BenchManager("config.properties");

        for( String s : r.getClassesNameList() ){
            System.out.println(s);
        }

        for( String taskid : r.getTaskIds() ){

            for (FieldValue fieldValue : r.getIteratedFields(taskid)) {

                System.out.println(fieldValue);
            }

            for (FieldValue fieldValue : r.getFieldsToSet(taskid)) {
                System.out.println(fieldValue);
            }

            System.out.println(taskid +" "+ r.getClassName(taskid));


            for (List<FieldValue> strings : r.getSettings(taskid)) {
                for (FieldValue string : strings) {
                    System.out.print(string + ", ");
                }
                System.out.println();
            }
        }



        for (String taskId : r.getTaskIds()) {
            String className = r.getClassName(taskId);


            String title = taskId+"_"+className;

            for (FieldValue field : r.getFieldsToSet(taskId)) {
                title += "_" + field.field + "-" + field.value;
            }

            for (List<FieldValue> settings : r.getSettings(taskId)) {

                String info = new String();
                for (FieldValue setting : settings) {
                    //setField("*", taskId, setting.field, setting.value);
                    info += "_" + setting.field + "-" + setting.value;
                }

                //setField("*", taskId, "title", title);

                //invokeBenchMark("*", 8, taskId);

                System.out.println(title+""+info);
            }


        }

    }

}
