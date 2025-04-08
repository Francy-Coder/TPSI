import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        ArrayList<Thread> threads = new ArrayList<>();

        // Add threads that add 1 to the counter
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "add"));
        }

        // Add threads that subtract 1 from the counter
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "sub"));
        }

        // Add threads that multiply the counter by 2
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "mult"));
        }

        // Start all threads
        for (Thread t : threads) {
            t.start();
        }

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
