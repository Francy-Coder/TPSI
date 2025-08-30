import java.util.concurrent.Semaphore;

public class StampaAlternataSemplice {
    private static final int numeroMassimo = 20;
    private static Semaphore semaforoDispari = new Semaphore(1);
    private static Semaphore semaforoPari = new Semaphore(0);
    
    public static void main(String[] args) {
        Thread threadDispari = new Thread(new StampaDispari());
        Thread threadPari = new Thread(new StampaPari());
        
        threadDispari.start();
        threadPari.start();
        
        try {
            threadDispari.join();
            threadPari.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nFinito di stampare!");
    }
    
    static class StampaDispari implements Runnable {
        @Override
        public void run() {
            for (int numero = 1; numero <= numeroMassimo; numero += 2) {
                try {
                    semaforoDispari.acquire();
                    System.out.println("Dispari: " + numero);
                    semaforoPari.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    static class StampaPari implements Runnable {
        @Override
        public void run() {
            for (int numero = 2; numero <= numeroMassimo; numero += 2) {
                try {
                    semaforoPari.acquire();
                    System.out.println("Pari:    " + numero);
                    semaforoDispari.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
