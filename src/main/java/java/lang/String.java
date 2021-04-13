package java.lang;

public class String {

    public java.lang.String toString() {
        return "hello";
    }

    /**
     * 运行下面方法会报错
     * 错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
     * public static void main(String[] args)
     * 否则 JavaFX 应用程序类必须扩展javafx.application.Application
     * <p>
     * 因为由于双亲委派模型:如果一个类加载器收到类加载的请求，它首先不会自己去尝试加载这个类
     * 而是把这个请求委派给父类加载器完成。每个类加载器都是如此
     * 只有当父加载器在自己的搜索范围内找不到指定的类时（即ClassNotFoundException）
     * 子加载器才会尝试自己去加载。
     * <p>
     * 为什么需要双亲委派
     * 在这里，先想一下，如果没有双亲委派，那么用户是不是可以自己定义一个java.lang.Object的同名类
     * java.lang.String的同名类，并把它放到ClassPath中
     * 那么类之间的比较结果及类的唯一性将无法保证
     * 因此，为什么需要双亲委派模型？防止内存中出现多份同样的字节码
     */
    public static void main(String[] args) {
        String str = new String();
        str.toString();
    }
}
