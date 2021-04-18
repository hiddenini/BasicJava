package interview.concurrentHashMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Sight {
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int i = 0; i < 5; i++) {
            map.put(i, i);
        }
        map.get(2);

        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(15);
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(i, i);
        }
    }
}
