package lambdaLearn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xz on 2019/10/28.
 */
public class CollectLearn {
    public static void main(String[] args) {
        /**
         * 将操作之后的结果转换为集合
         */
        List<Double> list= Arrays.asList(100.0,200.0,300.0);

        //List<Double> result=list.stream().filter(n -> n>=200).collect(Collectors.toList());

        //可以分解为下面的步骤,便于理解
        List<Double> result=list.stream().filter(n ->{
            System.out.println(n);
            return  n>=200? Boolean.TRUE:Boolean.FALSE;
        }).collect(Collectors.toList());

        System.out.println("toList  result:{}"+result);


        List<String> stringList= Arrays.asList("java","php","c++","js");

        String s=stringList.stream().map(n -> n.toUpperCase()).collect(Collectors.joining(","));
        System.out.println("join result:{}"+s);
    }
}
