import java.util.concurrent.Semaphore;
import java.util.Random;

class Stampante {
    private Semaphore semaforo = new Semaphore(1);
    private int documentiStampati = 0;

    public void stampa(String nomeThread, String nomeDoc) {
        try {
            System.out.println("[" + nomeThread + "] attende risorsa");
            semaforo.acquire();
            
            System.out.println("[" + nomeThread + "] inizia la stampa " + nomeDoc);
            
            Random rand = new Random();
            int tempoStampa = 500 + rand.nextInt(1000);
            Thread.sleep(tempoStampa);
            
            System.out.println("[" + nomeThread + "] finisce la stampa di " + nomeDoc);
            documentiStampati++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }

    public int getDocumentiStampati() {
        return documentiStampati;
    }
}

class UtenteStampante extends Thread {
    private Stampante stampante;
    private int numDocumenti;
    private String nomeThread;

    public UtenteStampante(Stampante stampante, int numDocumenti, String nomeThread) {
        this.stampante = stampante;
        this.numDocumenti = numDocumenti;
        this.nomeThread = nomeThread;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numDocumenti; i++) {
            String nomeDoc = "Documento_" + nomeThread + "_" + i;
            stampante.stampa(nomeThread, nomeDoc);
        }
    }
}

public class MainStampante {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        final int N = 10;
        final int D = 3;  
        
        Stampante stampante = new Stampante();
        Thread[] threads = new Thread[N];
        
        for (int i = 0; i < N; i++) {
            threads[i] = new UtenteStampante(stampante, D, "Utente" + (i+1));
            threads[i].start();
        }
        
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("\nDocumenti stampati: " + stampante.getDocumentiStampati());
        System.out.println("Tempo totale di esecuzione: " + totalTime + " ms");
    }
}
