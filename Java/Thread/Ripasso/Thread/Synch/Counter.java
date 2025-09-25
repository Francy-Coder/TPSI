import java.util.concurrent.Semaphore;

// La risorsa. NON è QUASI MAI UN THREAD
public class Counter {
    private long counter;
    Semaphore s;

    public Counter(){
        this.counter = 0;
        this.s = new Semaphore(1); //Non si deve mettere nel metodo add(), perchè il metodo viene richiamato (al interno di un ciclo for) da AdderThread.java e per ogni thread che viene creato, viene creato un semaforo nuovo
    }

    //Race condition(!!!!!)
    public void add(long valueToAdd) throws InterruptedException {
        s.acquire();
        this.counter += valueToAdd;
        s.release();
    }

    /*
    public synchronized add(long valueToAdd) throws InterruptedException {
        this.counter += valueToAdd;
    }*/

    public long getCounter() {
        return counter;
    }
}
