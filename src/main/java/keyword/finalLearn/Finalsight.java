package keyword.finalLearn;/**
 * Created by pc on 2019/11/12.
 */

/**
 * @author xz
 * @date 2019/11/12 16:35
 **/


/**
 * 被final修饰的变量，不管变量是在是哪种变量，切记不可变的是变量的引用而非引用指向对象的内容
 */
public class Finalsight {
    public static void main(String[] args) {
        final String s1="aa";
        final String s2="bb";
        //s1=s2;


        final String[] strs0 = {"123","234"};
        final String[] strs1 = {"345","456"};
        strs1[1] = "333";
        //strs1 = strs0;

    }
}
