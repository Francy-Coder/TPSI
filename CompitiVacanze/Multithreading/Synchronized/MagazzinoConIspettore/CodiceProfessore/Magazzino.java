package CompitiVacanze.MagazzinoConIspettore;

import java.util.ArrayList;

public class Magazzino {
    private ArrayList<Integer> storage;
    private int capacity;

    public Magazzino(int initialCapacity){
        this.capacity = initialCapacity;
        this.storage = new ArrayList<>();
    }

    public synchronized void inserisciNumero(int value) throws InterruptedException {
        while (storage.size() >= capacity) wait();

        storage.add(value);
        System.out.println("Produttore inserisce: " + value + ", Size: " + storage.size());
    }

    public int getCapacity() {
        return this.capacity;
    }
}
