public class Counter {
    private float value;

    public synchronized void add(float amount) {
        value += amount;
    }

    public synchronized void sub(float amount) {
        value -= amount;
    }

    public synchronized void mult(float factor) {
        value *= factor;
    }

    public float getValue() {
        return value;
    }
}
