public class Counter {

    private volatile int value;


    public void inc() {
        value++;
    }

    public void dec() {
        value--;
    }

    public int getValue() {
        return value;
    }
}
