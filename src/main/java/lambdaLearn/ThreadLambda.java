package lambdaLearn;

/**
 * Created by xz on 2019/10/28.
 */
public class ThreadLambda {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In Java7, thread starts !!");
            }
        }).start();
        /**
         * 使用lambda表达式替代匿名内部类
         * () -> {}替代了匿名内部类
         */
        new Thread(   () ->     System.out.println("In Java8, thread starts !!") ).start();
    }
}
