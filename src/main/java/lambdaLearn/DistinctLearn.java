package lambdaLearn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xz on 2019/10/28.
 */
public class DistinctLearn {
    public static void main(String[] args) {
        /**
         * distinct()去重
         */
        List<String> list= Arrays.asList("java","php","c++","js","c++","js","c#","java");
        List<String> result=list.stream().filter(n ->n.startsWith("c")).distinct().collect(Collectors.toList());
        System.out.println("result:{}"+result);
    }
}
