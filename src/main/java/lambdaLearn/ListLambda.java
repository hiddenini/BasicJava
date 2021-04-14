package lambdaLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by xz on 2019/10/28.
 */
public class ListLambda {

    /**
     * (parameters) -> expression ##一条语句的时候
     * (parameters) -> { statements; } ##多条语句的时候,需要return返回值的时候
     * () -> { statements; } ##没有参数
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("java", "php", "c++", "js");
/*        list.forEach(s -> System.out.println(s));

        System.out.println("下面这种也ok");

        list.forEach(System.out::println);*/

        Boolean flag = list.stream().anyMatch((s) -> {
            if (s.equals("php")) {
                return false;
            }
            return true;
        });
        System.out.println(flag);
        //test github commit


        List<Reward> judgeVos = new ArrayList<>();
        Reward reward1 = new Reward("1", "001");
        Reward reward2 = new Reward("2", "002");
        Reward reward3 = new Reward("3", "0013");
        Reward reward4 = new Reward("4", "004");
        judgeVos.add(reward1);
        judgeVos.add(reward2);
        judgeVos.add(reward3);
        judgeVos.add(reward4);

        boolean b = judgeVos.stream().anyMatch((j) -> "2".equals(j.getId()));
        System.out.println(b);

        /**
         * java.util.NoSuchElementException: No value present
         */
        Optional<Reward> rewardOptional = judgeVos.stream().filter(item -> item.getId().equals("8")).findFirst();
        System.out.println(rewardOptional.get());

/*        if (rewardOptional.isPresent()) {
            System.out.println(rewardOptional.get());
        }*/
    }
}
