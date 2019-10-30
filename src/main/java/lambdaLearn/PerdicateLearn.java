package lambdaLearn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by xz on 2019/10/28.
 */
public class PerdicateLearn {
    public static void main(String[] args) {
        /**
         * java8新增了一个包，叫做 java.util.function，接口里面包含了很多类，用来支持函数式编程，其中有一个接口就是Predicate,用Perdicate可以用来过滤
         */
        List<String> list= Arrays.asList("java","php","c++","js","c#");
        Predicate<String> p1=(n) -> n.startsWith("j");

        list.stream().filter(p1).forEach(n -> System.out.println(n));

        System.out.println("above is the same ");

        list.stream().filter((n) -> n.startsWith("j")).forEach(n -> System.out.println(n));

        System.out.println("above is the multi predicate ");

        /**
         * 若需要多个过滤条件，而不是单单的一个过滤条件，那么怎么实现呢。Predicate提供类似于逻辑操作符AND和OR的方法，名字叫做and()和or()，用于将传入 filter() 方法的条件合并起来
         */
        Predicate<String> p3=(n) -> n.startsWith("c");
        Predicate<String> p4=(n) -> n.length()==3;
        list.stream().filter(p3.and(p4)).forEach(n -> System.out.println(n));
        list.stream().filter(p3.or(p4)).forEach( System.out::print);
    }
}
