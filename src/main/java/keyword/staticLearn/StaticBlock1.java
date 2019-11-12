package keyword.staticLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 15:25
 **/


/**
 *  静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问。
 */
public class StaticBlock1 {
    static
    {
        c = 3;
        //System.out.println(c);
    }

    private static int c;

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
