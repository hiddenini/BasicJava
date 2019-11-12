package keyword.staticLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 15:29
 **/

/***
 * 静态代码块是严格按照父类静态代码块->子类静态代码块的顺序加载的，且只加载一次。
 */
public class StaticSon extends  StaticFather {
    static
    {
        System.out.println("StaticSon.static block");
    }

    public StaticSon()
    {
        System.out.println("StaticSon.constructor()");
    }

    public static void main(String[] args)
    {
        new StaticSon();
        System.out.println("second");
        new StaticSon();
    }
}
