package keyword.staticLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 15:21
 **/

/**
 * 输出:
 *      Enter StaticBlock.B()
 *      Enter StaticBlock.static block
 * 静态资源的加载顺序是严格按照静态资源的定义顺序来加载的
 */
public class StaticBlock {
    private static int a = B();

    static
    {
        System.out.println("Enter StaticBlock.static block");
    }

    public static void main(String[] args)
    {
        new StaticBlock();
    }

    public static int B()
    {
        System.out.println("Enter StaticBlock.B()");
        return 1;
    }
}
