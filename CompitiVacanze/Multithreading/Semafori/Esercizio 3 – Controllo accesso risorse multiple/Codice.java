import java.util.concurrent.Semaphore;

public class SalaPostazioni {
    private static final int numeroPostazioni = 3;
    private static final int numeroThread = 10;
    private static Semaphore semaforoPostazioni = new Semaphore(numeroPostazioni);
    
    public static void main(String[] args) {
        System.out.println("Sala con " + numeroPostazioni + " postazioni - Inizio simulazione");
       
        Thread[] threads = new Thread[numeroThread];
        for (int i = 0; i < numeroThread; i++) {
            threads[i] = new Thread(new Lavoratore(i + 1));
            threads[i].start();
        }
        
        for (int i = 0; i < numeroThread; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Tutti i thread hanno finito il lavoro!");
    }
    
    static class Lavoratore implements Runnable {
        private int id;
        
        public Lavoratore(int id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " vuole entrare in sala");
           
                semaforoPostazioni.acquire();
                
                System.out.println("Thread " + id + " è entrato in sala. Postazioni libere: " 
                                  + semaforoPostazioni.availablePermits());
         
                System.out.println("Thread " + id + " sta lavorando...");
                Thread.sleep(2000);
                
                System.out.println("Thread " + id + " ha finito il lavoro");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoPostazioni.release();
                System.out.println("Thread " + id + " ha lasciato la sala. Postazioni libere: " 
                                  + semaforoPostazioni.availablePermits());
            }
        }
    }
}
