package algorithm.singleton;

public class Singleton6 {
    private volatile Singleton6 instance;

    private Singleton6() {}

    public Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton6 a = new Singleton6();

        Singleton6 th1 = a.getInstance();
        Singleton6 th2 = a.getInstance();

        System.out.println(th1 == th2);

        System.out.println(th1.hashCode());
        System.out.println(th2.hashCode());
    }
}
