public class Main {
    public static void main(String[] args) {
        //Creare N Thread che vanno a lavorare sul
        Counter counter = new Counter();

        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new CounterThread(counter));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Valore finale del contatore: " + counter.getValue());
    }
}
