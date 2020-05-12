package lambdaLearn;

import java.util.ArrayList;
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
        //将list中大于等于200的元素生成一个新的list（过滤操作）
        List<Double> result=list.stream().filter(n ->{
            System.out.println(n);
            return  n>=200? Boolean.TRUE:Boolean.FALSE;
        }).collect(Collectors.toList());

        System.out.println("toList  result:{}"+result);

        List<Double> doubleList=new ArrayList<>();

        //将list中的每个元素扩大2倍后得 到新的集合(对每一个元素进行同样的操作)
        list = list.stream().map(n -> {
            System.out.println("n:" + n);
            return 2 * n;
        }).collect(Collectors.toList());

        System.out.println("doubleList:"+list);

        List<String> stringList= Arrays.asList("java","php","c++","js");

        String s=stringList.stream().map(n -> n.toUpperCase()).collect(Collectors.joining(","));
        System.out.println("join result:{}"+s);
    }
}
