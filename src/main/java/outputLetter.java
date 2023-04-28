import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class outputLetter {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Runnable task = null;
                    try {
                        task = blockingQueue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    new Thread(task).start();
                }
            }
        }).start();

        for(int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    System.out.print("A");
                }
            });
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    System.out.print("B");
                }
            });
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    System.out.print("C");
                }
            });
        }
    }
}
