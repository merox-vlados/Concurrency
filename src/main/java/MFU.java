public class MFU {

    private Object monitor1 = new Object();
    private Object monitor2 = new Object();
    public void print(int numberOfPages) {
        synchronized (monitor1) {
            for (int i = 1; i <= numberOfPages; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("\nОтпечатано %s стр.", i);
            }
        }
    }

    public void scan(int numberOfPages) {
        synchronized (monitor2) {
            for (int i = 1; i <= numberOfPages; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("\nОтсканировано %s стр.", i);
            }
        }
    }
}
