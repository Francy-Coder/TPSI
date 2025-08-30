import java.util.concurrent.Semaphore;

public class BarrieraSemplice {
    private static final int numeroThread= 5;
    private static int contatore = 0;
    
    private static Semaphore semaforoContatore = new Semaphore(1);

    private static Semaphore semaforoBarriera = new Semaphore(0);
    
    public static void main(String[] args) {
        System.out.println("Barriera per " + numeroThread+ " thread - Inizio");
        
        Thread[] threads = new Thread[NUMERO_THREAD];
        for (int i = 0; i < NUMERO_THREAD; i++) {
            threads[i] = new Thread(new Partecipante(i + 1));
            threads[i].start();
        }
        
        for (int i = 0; i < NUMERO_THREAD; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Tutti i thread hanno completato!");
    }
    
    static class Partecipante implements Runnable {
        private int id;
        
        public Partecipante(int id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " sta lavorando prima della barriera...");
                Thread.sleep((long)(Math.random() * 2000)); 
                
                System.out.println("Thread " + id + " è arrivato alla barriera");
             
                semaforoContatore.acquire(); 
                contatore++;
                
                if (contatore == NUMERO_THREAD) {
                    System.out.println("=== TUTTI I THREAD SONO ARRIVATI! ===");
                    System.out.println("Sblocco di tutti i thread...");
                    semaforoBarriera.release(NUMERO_THREAD); 
                }
                semaforoContatore.release();
                
                semaforoBarriera.acquire();
             
                System.out.println("Thread " + id + " ha superato la barriera e continua il lavoro...");
                Thread.sleep((long)(Math.random() * 1000));
                
                System.out.println("Thread " + id + " ha completato tutto il lavoro");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
