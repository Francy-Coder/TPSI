import java.util.concurrent.Semaphore;

public class BufferCircolareSemplice {
    private static final int dimensione = 5;
    private static int[] buffer = new int[dimensione];
    private static int posizioneInserisci = 0;
    private static int posizionePrendi = 0;
    
    private static Semaphore semaforoElementiPronti = new Semaphore(0);
    private static Semaphore semaforoSpaziLiberi = new Semaphore(dimensione);
    private static Semaphore semaforoProtezione = new Semaphore(1);
    
    public static void main(String[] args) {
        Thread produttore = new Thread(new Produttore());
        Thread consumatore = new Thread(new Consumatore());
        
        produttore.start();
        consumatore.start();
        
        try {
            Thread.sleep(5000); 
            produttore.interrupt();
            consumatore.interrupt();
            
            produttore.join();
            consumatore.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Fine programma!");
    }
    
    static class Produttore implements Runnable {
        @Override
        public void run() {
            int numero = 1;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaforoSpaziLiberi.acquire();
              
                    semaforoProtezione.acquire();
                 
                    buffer[posizioneInserisci] = numero;
                    System.out.println("Prodotto: " + numero + " in posizione " + posizioneInserisci);
       
                    posizioneInserisci = (posizioneInserisci + 1) % dimensione;
      
                    semaforoProtezione.release();
           
                    semaforoElementiPronti.release();
                    
                    numero++;
                    Thread.sleep(1000); 
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    static class Consumatore implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
        
                    semaforoElementiPronti.acquire();
                    
                    semaforoProtezione.acquire();
                  
                    int numeroPreso = buffer[posizionePrendi];
                    System.out.println("Consumato: " + numeroPreso + " da posizione " + posizionePrendi);
                    
                    posizionePrendi = (posizionePrendi + 1) % dimensione;
                 
                    semaforoProtezione.release();
                  
                    semaforoSpaziLiberi.release();
                    
                    Thread.sleep(1500); 
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
