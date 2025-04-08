public class CounterThread extends Thread {
    //E' meglio estendere Thread, visto che se si implementa la classe runnable, si puo solo usare il metodo run

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
