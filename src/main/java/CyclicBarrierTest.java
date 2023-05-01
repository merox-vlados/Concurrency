import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for(int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long millis = (long)(Math.random() * 5000 + 1000);
                    String name = Thread.currentThread().getName();
                    System.out.println(name + ": Data is being prepared");
                    try {
                        Thread.sleep(millis);
                        System.out.println(name + ": Data is ready");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(name + ": Continue work");
                }
            }).start();
        }
    }


    private static void workWithFileSystem() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started working with file system.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " finished working with file system.");
    }
}
