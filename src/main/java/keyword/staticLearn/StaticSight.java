package keyword.staticLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 15:19
 **/

/**
 * 静态资源属于类，但是是独立于类存在的。从JVM的类加载机制的角度讲，静态资源是类初始化的时候加载的，而非静态资源是类new的时候加载的。
 *
 * 1、静态方法能不能引用非静态资源？不能，new的时候才会产生的东西，对于初始化后就存在的静态资源来说，根本不认识它。

 2、静态方法里面能不能引用静态资源？可以，因为都是类初始化的时候加载的，大家相互都认识。

 3、非静态方法里面能不能引用静态资源？可以，非静态方法就是实例方法，那是new之后才产生的，那么属于类的内容它都认识。
 */
public class StaticSight {
    private int i=0;
    public static void main(String[] args) {

        //i=9;
    }
}
