public class CounterThread implements Runnable {
    //Ogni istanza di questo thread deve sommare 1 al contatore (Unico) x 10k volte

    private final Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.add(1);
        }
    }
}
