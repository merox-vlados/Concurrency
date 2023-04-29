import java.util.concurrent.CountDownLatch;

public class mfuMain {

    public static void main(String[] args) {
        MFU mfu = new MFU();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print(5);

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan(6);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan(7);

            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
