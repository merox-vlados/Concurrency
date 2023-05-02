
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.Semaphore;

public class Race {
    public static void main(String[] args) {
        HashMap<String,Long> hashMap = new HashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long millis = (long)(Math.random() * 5000 + 1000);
                    String name = "Автомобиль - " + index;
                    startPrepare(name);
                    try {
                        Thread.sleep(millis);
                        finishPrepare(name);
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long before = System.currentTimeMillis();
                    startRoadFirst(name);
                    finishRoadFirst(name);
                    try {
                        semaphore.acquire();
                        startTunnel(name);
                        finishTunnel(name);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                    startRoadSecond(name);
                    finishRoadSecond(name);
                    long after = System.currentTimeMillis();
                    long totalTime = millis + (after - before);
                    hashMap.put(name,totalTime);
                }
            }).start();
        }
        minValue(hashMap);
    }

    private static void startPrepare(String name) {
        System.out.println(name + " начал подготовку к гонке");
    }

    private static void finishPrepare(String name) {
        System.out.println(name + " закончил подготовку к гонке");
    }

    private static void startRoadFirst(String name) {
        System.out.println(name + " начал участок обычной дороги №1");
    }

    private static void finishRoadFirst(String name) {
        System.out.println(name + " закончил участок обычной дороги №1");
    }

    private static void startTunnel(String name) {
        System.out.println(name + " начал туннель");
    }

    private static void finishTunnel(String name) {
        System.out.println(name + " законил туннель");
    }

    private static void startRoadSecond(String name) {
        System.out.println(name + " начал участок обычной дороги №2");
    }

    private static void finishRoadSecond(String name) {
        System.out.println(name + " закончил участок обычной дороги №2");
    }

    public static void minValue(HashMap hashMap) {
        long min = Long.MAX_VALUE;
        String name = null;
        for (Object key : hashMap.keySet()) {
            if ((long)(hashMap.get(key)) < min) {
                min = (long) hashMap.get(key);
                name = (String) key;
            }
        }
        System.out.println(name + " победитель " + min);
    }





}
