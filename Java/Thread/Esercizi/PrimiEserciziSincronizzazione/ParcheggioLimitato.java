import java.util.concurrent.Semaphore;
import java.util.Random;

class Parcheggio {
    private Semaphore postiDisponibili;
    private int postiLiberi;

    public Parcheggio(int postiTotali) {
        this.postiDisponibili = new Semaphore(postiTotali, true);
        this.postiLiberi = postiTotali;
    }

    public void entra(String nomeAuto) throws InterruptedException {
        System.out.println("[" + nomeAuto + "] attende un posto libero");
        postiDisponibili.acquire();
        
        synchronized(this) {
            postiLiberi--;
        }
        
        System.out.println("[" + nomeAuto + "] è entrata nel parcheggio. Posti rimasti: " + postiLiberi);
      
        Random rand = new Random();
        Thread.sleep(1000 + rand.nextInt(2000));
        
        esci(nomeAuto);
    }

    public synchronized void esci(String nomeAuto) {
        postiLiberi++;
        postiDisponibili.release();
        System.out.println("[" + nomeAuto + "] è uscita dal parcheggio. Posti rimasti: " + postiLiberi);
    }
}

class Auto extends Thread {
    private Parcheggio parcheggio;
    private String nomeAuto;

    public Auto(Parcheggio parcheggio, String nomeAuto) {
        this.parcheggio = parcheggio;
        this.nomeAuto = nomeAuto;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                parcheggio.entra(nomeAuto);
            }
        } catch (InterruptedException e) {
            System.out.println("[" + nomeAuto + "] interrotta mentre attendeva");
        }
    }
}

public class MainParcheggio {
    public static void main(String[] args) {
        final int POSTI_TOTALI = 3;
        final int NUM_AUTO = 10;
        final int TEMPO_APERTURA_MS = 10000; 
        
        Parcheggio parcheggio = new Parcheggio(POSTI_TOTALI);
        Auto[] auto = new Auto[NUM_AUTO];
        
        for (int i = 0; i < NUM_AUTO; i++) {
            auto[i] = new Auto(parcheggio, "Auto" + (i+1));
            auto[i].start();
        }
        
        try {
            Thread.sleep(TEMPO_APERTURA_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (Auto a : auto) {
            a.interrupt();
        }
        
        System.out.println("Parcheggio chiuso dopo " + TEMPO_APERTURA_MS + " ms");
    }
}
