public class CounterThread extends Thread {
    private Counter counter;
    private String operation;

    public CounterThread(Counter counter, String operation) {
        this.counter = counter;
        this.operation = operation;
    }

    @Override
    public void run() {
        switch (operation) {
            case "add":
                for (int i = 0; i < 10000; i++) {
                    counter.add(1);
                }
                break;
            case "sub":
                for (int i = 0; i < 10000; i++) {
                    counter.sub(1);
                }
                break;
            case "mult":
                for (int i = 0; i < 2; i++) {
                    counter.mult(2);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }
}
