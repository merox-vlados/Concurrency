import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Semaphore {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);
        for(int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " started working.");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        semaphore.acquire();
                        workWithFileSystem();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }

                    System.out.println(name + " finished working.");
                }
            });
        }
        executorService.shutdown();
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
