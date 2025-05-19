package algorithm.singleton;

public class Singleton3 {
}

class YunhaSync2 {
    private static String yunha = "오르트구름";

    public static void main(String[] args) {
        YunhaSync2 a = new YunhaSync2();
        new Thread(() -> {
            for (int i=0;i<10;i++) {
                a.say("사건의 지평선");
            }
        }).start();

        new Thread(() -> {
            for (int i=0;i<10;i++) {
                a.say("오르트구름");
            }
        }).start();
    }

    public synchronized void say(String song) {
        yunha = song;

        try {
            long sleep = (long) (Math.random() * 100);
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (yunha.equals(song)) {
            System.out.println(song + "|" + yunha);
        }
    }
}
