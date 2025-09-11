import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int capacity = 5;
        final int numProducers = 10; //assicurarsi che numProducers sia strettamente maggiore di capacity
        final int sogliaIspettore = 50;

        Magazzino magazzino = new Magazzino(capacity, sogliaIspettore);

        // start producers
        ArrayList<Producer> producers = new ArrayList<>();
        for (int i = 0; i < numProducers; i++) producers.add(new Producer(i, magazzino));
        for (Producer p : producers) p.start();

        // un consumatore just for fun, si può e deve testare con più consumatori.
        Thread consumer = new Consumer(1, magazzino);
        consumer.start();

        // Ispettore
        Thread inspector = new Inspector(magazzino, 3000);
        inspector.setDaemon(true); // Piccolo trick non strettamente necessario ;)
        inspector.start();

        // Il programma sarebbe tendenzialmente infinito... ci metto un limite di tempo così da chiudere in automatico.
        try {
            Thread.sleep(12000);
        } catch (InterruptedException ignored) {
        }

        for (Thread t : producers) t.interrupt();
        consumer.interrupt();
        inspector.interrupt();

        System.out.println("Main exiting.");
    }
}

