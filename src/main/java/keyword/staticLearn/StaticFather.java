package keyword.staticLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 15:29
 **/

public class StaticFather {
    static
    {
        System.out.println("StaticFather.static block");
    }

    public StaticFather()
    {
        System.out.println("StaticFather.constructor()");
    }
}
