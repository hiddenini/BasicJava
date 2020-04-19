package spi;

public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("wang wang wang");
    }
}
