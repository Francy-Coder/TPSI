public class Counter {
    private float value;

    public synchronized void add(float amount) {
        value += amount;
    }

    public float getValue() {
        return value;
    }
}
