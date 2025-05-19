package algorithm.singleton;

public class Singleton4 {
//    private final static Singleton4 instance = new Singleton4();
    private static final Singleton4 instance;

    static {
       instance = new Singleton4();
    }

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return instance;
    }
}
