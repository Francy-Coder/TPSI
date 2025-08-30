import java.util.concurrent.Semaphore;

public class BarrieraSemplice {
    private static final int numeroThread= 5;
    private static int contatore = 0;
    
    private static Semaphore semaforoContatore = new Semaphore(1);

    private static Semaphore semaforoBarriera = new Semaphore(0);
    
    public static void main(String[] args) {
        System.out.println("Barriera per " + numeroThread+ " thread - Inizio");
        
        Thread[] threads = new Thread[numeroThread];
        for (int i = 0; i < numeroThread; i++) {
            threads[i] = new Thread(new Partecipante(i + 1));
            threads[i].start();
        }
        
        for (int i = 0; i < numeroThread; i++) {
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
                
                if (contatore == numeroThread) {
                    System.out.println("=== Tutti i thread sono arrivati ===");
                    System.out.println("Sblocco di tutti i thread...");
                    semaforoBarriera.release(numeroThread); 
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
