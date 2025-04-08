public class CounterThread extends Thread {
    public enum Operation {
        ADD, SUB, MULT
    }

    private Counter counter;
    private Operation operation;

    public CounterThread(Counter counter, Operation operation) {
        this.counter = counter;
        this.operation = operation;
    }

    @Override
    public void run() {
        switch (operation) {
            case ADD:
                for (int i = 0; i < 10000; i++) {
                    counter.add(1);
                }
                break;
            case SUB:
                for (int i = 0; i < 10000; i++) {
                    counter.sub(1);
                }
                break;
            case MULT:
                for (int i = 0; i < 2; i++) {
                    counter.mult(2);
                }
                break;
        }
    }
}
