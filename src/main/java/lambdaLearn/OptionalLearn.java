package lambdaLearn;

import po.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xz on 2019/10/28.
 */
public class OptionalLearn {
    public static void main(String[] args) {
        Optional<String> stringOptional=Optional.of("shanghai");
        System.out.println(stringOptional.isPresent());
        System.out.println(stringOptional.get());

        List<Double> list= Arrays.asList(100.0,200.0,300.0);

        Optional<Double> s=list.stream().filter(n -> n>=200).reduce((sum,n) -> sum+n);

        //分解成步骤
/*        Optional<Double> s=list.stream().filter(n -> n>=200).reduce((sum,n) -> {
            sum=sum+n;
            return sum;
        });*/

        //这种没下面那种好
        if(s.isPresent()){
            System.out.println(s.get());
        }

        //使用下面的方法
        s.ifPresent(System.out::println);

        s.ifPresent(n -> System.out.println(n));

        s.ifPresent(n -> {
            double a=n;
            System.out.println(a);
        });


        Optional<String> empty=Optional.empty();
        System.out.println(empty.orElse("There is no value present!"));

        System.out.println(empty.orElseGet(() -> "Default Value"));

        /**
         * 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional.
         */
        Optional<String> name=Optional.of("shanghai");
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        /**
         * flatMap与map（Function）非常类似，区别在于传入方法的lambda表达式的返回类型。
         * map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
         * 但flatMap方法中的lambda表达式返回值必须是Optionl实例。
         */
        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));//输出SANAULLA

        /**
         * 如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional。
         */
        Optional<String> longName = name.filter((value) -> value.length() > 10);
        System.out.println(longName.orElse("The name is less than 6 characters"));//输出Sanaulla



        List<Double> doubleList= Arrays.asList(100.0,200.0,300.0);

        User u=User.builder()
                .name("zjw")
                .list(doubleList)
                .build();

        Optional<User> userOptional=Optional.of(u);

        List<Double> result=userOptional.map(user-> user.getList()).orElse(Collections.EMPTY_LIST);

        System.out.println("result:"+result);

        /**
         * 将user中的list中大于300.0的重新生成一个集合后当做map的mapping
         */
        List<Double> result1=userOptional.map(user-> user.getList().stream().filter(n -> n>300).collect(Collectors.toList())).orElse(Collections.EMPTY_LIST);

        System.out.println("result1:"+result1);

        //得到一个泛型为String的Optional
        Optional<String>   stringOptional1=  userOptional.map(user -> user.getName());
        //还是得到一个泛型为String的Optional
        Optional<String>  stringOptional2 =stringOptional1.map(na -> na.toUpperCase());

        //如果在这里将name直接置为null，下面就会报错No value present
        //Optional<String>  stringOptional2 =stringOptional1.map(na -> null);

        System.out.println("two step nameResult:"+stringOptional2.get());

       String nameResult= userOptional.map(user -> user.getName()).map(na -> na.toUpperCase()).get();

        System.out.println("nameResult:"+nameResult);

        userOptional.map(user -> user.getName())
                .map(name1 -> name1.toUpperCase())
                .orElse(null);



        //当name为空的时候测试下map
        User user1=User.builder()
                .list(doubleList)
                .build();

        System.out.println(user1);

        Optional<User> userOptional1= Optional.of(user1);

        /**
         * map 中的mapping是一个lambda表达式,如果是单条语句，那么可以略去return语句。如果有多条语句，则需要return语句
         */
        Optional<String>  o= userOptional1.map(uu -> {
            uu.getName();
            return "aaa";
        });

        System.out.println(o.isPresent());

        o.ifPresent(System.out::println);



    }
}
