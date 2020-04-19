package spi;

import java.util.ServiceLoader;

/**
 * (1) 读取META-INF/services/下的配置文件，获得所有能被实例化的类的名称
 * (2) 通过反射方法Class.forName()加载类对象，并用instance()方法将类实例化。
 * (3) 把实例化后的类缓存到providers对象中，(LinkedHashMap<String,S>类型）
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        for (Animal s : animals) {
            s.say();
        }
    }
}
