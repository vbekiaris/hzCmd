package tests;

import com.hazelcast.query.Predicate;

import java.util.Map;

public class EvenKey implements Predicate<Integer, Object> {

    public boolean apply(Map.Entry<Integer, Object> entry) {
        int key = entry.getKey();
        return key % 2 == 0;
    }
}

