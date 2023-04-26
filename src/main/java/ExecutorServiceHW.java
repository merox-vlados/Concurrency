import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceHW {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long before = System.currentTimeMillis();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i = 0; i < 1_000_000; i ++) {
                    if(i % 2 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Сумма всех чётных чисел: " + sum);
                countDownLatch.countDown();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                long sum = 0;
                for(int i = 0; i < 1_000_000; i++) {
                    if(i % 7 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Сумма всех чисел делящихся на 7 без остатка: " + sum);
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            List<Integer> randomNumbers = new ArrayList<>();
            @Override
            public void run() {
                int count = 0;
                for(int i = 0; i < 1000; i++) {
                    double random = Math.random() * i;
                    randomNumbers.add((int)random);
                }
                for(Integer integer : randomNumbers) {
                    if(integer % 2 == 0) {
                        count++;
                    }
                }
                System.out.println("Количество всех чётных чисел: " + count);
                countDownLatch.countDown();
            }
        });
        executorService.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long after = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (after-before));

    }
}
