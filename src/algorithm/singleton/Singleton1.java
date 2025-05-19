package algorithm.singleton;

public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

class Main {
    public static void main(String[] args) {
        Singleton1 a = Singleton1.getInstance();
        Singleton1 b = Singleton1.getInstance();

        System.out.println(a == b);
    }
}