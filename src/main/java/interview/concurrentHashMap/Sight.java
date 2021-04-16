package interview.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class Sight {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(15);
        for (int i = 0; i < 5; i++) {
            map.put(i, i);
        }
    }
}
