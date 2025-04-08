import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        ArrayList<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "add"));
        }

        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "sub"));
        }

        for (int i = 0; i < 4; i++) {
            threads.add(new CounterThread(c, "mult"));
        }

        for (Thread t : threads) {
            t.start();
        }
        
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
