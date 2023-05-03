package ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Starter {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);

        List<MyCallable> tasks = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            MyCallable mc = new MyCallable();
            tasks.add(mc);
        }

       Long aLong = es.invokeAny(tasks);
        System.out.println(aLong);


        System.out.println("FINISH");
        es.shutdown();
    }
}

class  MyCallable implements Callable<Long> {
    @Override
    public Long call() {
        try {
            System.out.println("Started: " + Thread.currentThread().getId());
            Thread.sleep(1000 + Math.round(Math.random()* 5000));
            System.out.println("Finished: " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return Thread.currentThread().getId();
    }
}
