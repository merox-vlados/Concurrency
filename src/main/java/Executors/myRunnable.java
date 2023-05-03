package Executors;

public class myRunnable implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Start Runnable: " + Thread.currentThread().getId());
            System.out.println("Start Runnable: " + Thread.currentThread().getClass());
            Thread.sleep(5000);
            System.out.println("Finish Runnable: " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
