public class ATM_HW {
    private static double amountMoney = 1000;

    private static Object monitor = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                drawMoney("Николай", 103.56);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                drawMoney("Димон", 993);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                drawMoney("Kate", 554);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }

    public static void drawMoney (String name, double money) {
        System.out.println(name + " подошёл к банкомату");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (monitor) {
            if (money < amountMoney) {
                System.out.println(name + " вывел " + money + " рублей");
                amountMoney = amountMoney - money;
                System.out.println("В банкомате осталось " + amountMoney + "рублей");
            } else if (money > amountMoney) {
                System.out.println("В банкомате недостаточно денег для " + name);
            }
        }
    }
}
