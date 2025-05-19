package algorithm.singleton;

public class Singleton5 {
    private Singleton5() {}
    private static final Singleton5 SINGLETON_INSTANCE_HOLDER;

    static {
        SINGLETON_INSTANCE_HOLDER = new Singleton5();
        System.out.println("최초 로드될 때 초기화");
    }

    public Singleton5 getInstance() {
        return Singleton5.SINGLETON_INSTANCE_HOLDER;
    }


    public static void main(String[] args) {
        Singleton5 a = Singleton5.SINGLETON_INSTANCE_HOLDER;
        Singleton5 b = Singleton5.SINGLETON_INSTANCE_HOLDER;

        System.out.println(a == b);
    }
}
