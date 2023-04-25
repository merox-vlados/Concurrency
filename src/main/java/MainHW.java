public class MainHW {
    public static void main(String[] args) {
        withoutConcurrency();
        withConcurrency();
    }

    public static void withoutConcurrency() {
        float[] arr = new float[10_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
        }
        long before = System.currentTimeMillis();
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before + " милисекунд");
    }

    public static void withConcurrency() {
        float[] arr = new float[10_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
        }
        long before = System.currentTimeMillis();
        float src[] = new float[arr.length/2];
        float dst[] = new float[arr.length/2];

        System.arraycopy(arr,  0, src, 0,src.length);
        System.arraycopy(arr, src.length, dst, 0, dst.length);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < src.length; i++) {
                    src[i] = (float)(src[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < dst.length; i++) {
                    dst[i] = (float)(dst[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.arraycopy(src, 0, arr, 0, src.length);
        System.arraycopy(dst,0, arr, src.length, dst.length);


        long after = System.currentTimeMillis();
        System.out.println(after - before + " милисекунд");
    }
}
