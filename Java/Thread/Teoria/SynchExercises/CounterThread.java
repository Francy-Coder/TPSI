import java.util.concurrent.Semaphore;
public class CounterThread extends Thread {
    //E' meglio estendere Thread, visto che se si implementa la classe runnable, si puo solo usare il metodo run

    //Ogni istanza di questo thread deve sommare 1 al contatore (Unico) x 10k volte

    private Counter counter;

    private static final Semaphore sem = new Semaphore(1);

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                System.out.println("Interrotto");
            }
            counter.add(1);
            sem.release();
        }
    }
}
