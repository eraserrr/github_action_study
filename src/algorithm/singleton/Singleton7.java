package algorithm.singleton;

public class Singleton7 {

    public enum SingletonEnum {  
        INSTANCE;

        public void oortCloud() {

        }
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;

        System.out.println(instance2 == instance);
    }
}
