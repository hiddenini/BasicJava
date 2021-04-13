package jvm.entity;

public class Car {
    public int age;

    /**
     * 类加载器
     * 1--虚拟机自带的加载器
     * 2--启动类(根)加载器
     * 3--扩展加载器
     * 4--应用程序加载器
     * <p>
     * 流程
     * 1--类加载器收到类加载的请问求
     * 2--将这个类向上委托给父加载器去完成,一直向上委托,直到启动类加载器(根加载器)
     * 3--启动加载器检查是否能够加载当前这个类,能加载就使用当前类加载器加载,否则就抛出异常,通知子类进行加载
     * 4--重读步骤3
     * 5--全部都夹加载不到则报错:class not found
     */
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());
        Class<? extends Car> aClass = car1.getClass();
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());//AppClassLoader@18b4aac2
        /**
         * 在/jre/lib/ext中
         */
        System.out.println(aClass.getClassLoader().getParent());//ExtClassLoader@61bbe9ba
        /**
         * 结果为null 表示可能没有 或者java获取不到
         * 实际是肯定存在的,只是说用c++写的 Java程序获取不到
         * 在rt.jar中
         */
        System.out.println(aClass.getClassLoader().getParent().getParent());//null

    }
}
