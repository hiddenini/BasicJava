package spi;

public class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("miao miao miao");
    }
}
