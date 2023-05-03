package FutureAndCallable;

import java.util.concurrent.*;

public class Starter {
    public static void main(String[] args) throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(5);

        Future<Integer> sub = es.submit(new MyCallable());

        Thread.sleep(1000);
        sub.cancel(true);
//        Integer id = sub.get();
//        System.out.println(id);
        System.out.println(sub.isCancelled());

        System.out.println("Shutdown");
        es.shutdown();
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        try {
            System.out.println("Started: " + Thread.currentThread().getId());
            Thread.sleep(1);
            long d1 = System.currentTimeMillis();
            long d2 = System.currentTimeMillis();
            while(d2 < d1 + 5000) {
                d2 = System.currentTimeMillis();
            }
            System.out.println("Finished: " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        return 99;
    }
}
