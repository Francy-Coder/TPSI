import java.util.Random;

public class Consumer extends Thread {
    private final int threadId;
    private final Magazzino magazzino;
    private final Random rnd = new Random();

    public Consumer(int threadId, Magazzino magazzino) {
        this.threadId = threadId;
        this.magazzino = magazzino;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                magazzino.consumeValue();
                Thread.sleep(300 + rnd.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Consumer " + threadId + ": ended...");
    }
}
