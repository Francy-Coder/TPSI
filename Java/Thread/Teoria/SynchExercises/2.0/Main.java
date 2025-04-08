import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();

        ArrayList<Thread> threads = new ArrayList<>();

        // Create threads for addition
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, CounterThread.Operation.ADD));
        }

        // Create threads for subtraction
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, CounterThread.Operation.SUB));
        }

        // Create threads for multiplication
        for (int i = 0; i < 2; i++) {
            threads.add(new CounterThread(c, CounterThread.Operation.MULT));
        }

        // Start all threads
        for (Thread t : threads) t.start();

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " Interrotto");
            }
        }

        System.out.println(c.getValue());
    }
}
