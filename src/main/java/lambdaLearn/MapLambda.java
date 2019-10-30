package lambdaLearn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xz on 2019/10/28.
 */
public class MapLambda {
    public static void main(String[] args) {
        List<Double> list= Arrays.asList(100.0,200.0,300.0);
        /**
         * map()统一对元素进行操作
         */
        //将每个元素乘以0.2之后输出
        list.stream().map(n ->0.2*n).forEach(System.out::println);
    }
}
