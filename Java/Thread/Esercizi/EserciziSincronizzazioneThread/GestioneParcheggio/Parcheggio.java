import java.util.Random;
import java.util.concurrent.Semaphore;

class Parcheggio {
    private Semaphore postiDisponibili;
    private int postiTotali;
    private Random random = new Random();

    public Parcheggio(int postiTotali) {
        this.postiTotali = postiTotali;
        this.postiDisponibili = new Semaphore(postiTotali, true);
    }

    public boolean entra(Auto auto) throws InterruptedException {
        System.out.println(auto.getNome() + " sta cercando di entrare nel parcheggio...");
        
        if (postiDisponibili.tryAcquire()) {
            System.out.println(auto.getNome() + " è entrata nel parcheggio. Posti disponibili: " + 
                             (postiTotali - postiDisponibili.availablePermits()));
            return true;
        } else {
            System.out.println(auto.getNome() + " in attesa di un posto libero...");
            postiDisponibili.acquire();
            System.out.println(auto.getNome() + " finalmente entra nel parcheggio dopo l'attesa. Posti disponibili: " + 
                             (postiTotali - postiDisponibili.availablePermits()));
            return true;
        }
    }

    public void esci(Auto auto) {
        postiDisponibili.release();
        System.out.println(auto.getNome() + " è uscita dal parcheggio. Posti disponibili: " + 
                         (postiTotali - postiDisponibili.availablePermits()));
    }

    public int getPostiDisponibili() {
        return postiDisponibili.availablePermits();
    }
}
