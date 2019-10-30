package lambdaLearn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xz on 2019/10/28.
 */
public class ListLambda {

    /**
     *          (parameters) -> expression ##一条语句的时候
     *          (parameters) -> { statements; } ##多条语句的时候,需要return返回值的时候
     *          () -> { statements; } ##没有参数
     * @param args
     */
    public static void main(String[] args) {
        List<String> list= Arrays.asList("java","php","c++","js");
        list.forEach(s -> System.out.println(s));

        System.out.println("下面这种也ok");

        list.forEach(System.out::println);

        //test github commit
    }
}
