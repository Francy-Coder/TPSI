import java.util.ArrayList;
import java.util.List;

public class Magazzino {
    private final List<Integer> storage = new ArrayList<>();
    private final int capacity;
    private final int sogliaIspettore;

    public Magazzino(int capacity, int sogliaIspettore) {
        this.capacity = capacity;
        this.sogliaIspettore = sogliaIspettore;
    }

    public synchronized void addNumber(int value) throws InterruptedException {
        while (storage.size() >= capacity) wait();

        //Si può scegliere di inserire qui la logica di gestione dell'input di un producer.
        //Io preferisco inserirla nel thread in quanto non è responsabilità di questo metodo.

        storage.add(value);
        System.out.println("Warehouse: put " + value + " (size=" + storage.size() + ")");
        notifyAll();
    }

    public synchronized int consumeValue() throws InterruptedException {
        while (storage.isEmpty()) wait();

        //Interpretazione: rimuoviamo l'ultimo dallo storage
        int val = storage.remove(storage.size() - 1);
        System.out.println("Warehouse: consumed " + val + " (size=" + storage.size() + ")");

        notifyAll();
        return val;
    }

    // Chiamato dall'ispettore ogni 3 secondi
    public synchronized void ispezionaMagazzino() {
        int sum = computeStorageSum();
        System.out.println("L'ispettore controlla il magazzino. totale=" + sum + ", size=" + storage.size());
        if (sum >= sogliaIspettore) {
            System.out.println("Ispettore: totale >= " + sogliaIspettore + " -> RESET magazzino, totale=" + sum);
            storage.clear();
            notifyAll();
        } else {
            // Se c'è spazio metti la somma nel primo slot, altrimenti skip
            if (storage.size() < capacity) {
                storage.add(0, sum);
                System.out.println("Ispettore: inserisco " + sum + " nel primo slot.");
                notifyAll();
            } else {
                System.out.println("Ispettore: magazzino pieno, non inserisco nulla. SKIP.");
            }
        }
    }

    private int computeStorageSum() {
        int sum = 0;
        for (Integer elem : storage) sum += elem;
        return sum;
    }
}
