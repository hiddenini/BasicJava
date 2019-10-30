package lambdaLearn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2019/10/28.
 */
public class ReduceLearn {
    public static void main(String[] args) {
        /**
         * reduce() 函数可以将所有值合并成一个。Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。
         * 另外，reduce 并不是一个新的操作，你有可能已经在使用它。
         * SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接收多个值并返回一个值。
         * 流API定义的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。
         * IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作
         * 也有mapToLong()、mapToDouble() 方法来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。
         */
        List<Double> list= Arrays.asList(100.0,200.0,300.0);
        System.out.println(list.stream().map(n ->n*0.2).reduce(  (sum,n) ->sum+n).get());

        //拆成下面这样，便于初次理解
        double d=list.stream().map(n -> n*0.2).reduce((sum,n) -> {
            sum=sum+n;
            System.out.println("sum:"+sum);
            return sum;
        }).get();

        System.out.println("d:"+d);
    }
}
