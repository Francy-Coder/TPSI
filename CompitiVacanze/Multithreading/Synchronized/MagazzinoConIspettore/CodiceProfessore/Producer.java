import java.util.Random;

public class Producer extends Thread {

    private Random rnd;
    private Magazzino magazzino;
    private int threadId;

    public Producer(int threadId, Magazzino magazzino) {
        this.rnd = new Random();
        this.magazzino = magazzino;
        this.threadId = threadId;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            //Inizializzo a -1 perchè è dispari...
            int numberToAdd = -1;
            while (numberToAdd % 2 != 0) numberToAdd = rnd.nextInt(50) * 2;
            try {
                magazzino.addNumber(numberToAdd);
                Thread.sleep(200 + rnd.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Producer " + threadId + ": end...");
    }

}
