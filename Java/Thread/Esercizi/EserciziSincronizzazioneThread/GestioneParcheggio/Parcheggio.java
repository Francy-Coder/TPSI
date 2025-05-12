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
            postiDisponibili.acquire(); // Attende finché non si libera un posto
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

class Auto extends Thread {
    private Parcheggio parcheggio;
    private Random random = new Random();
    private String nome;

    public Auto(String nome, Parcheggio parcheggio) {
        super(nome);
        this.nome = nome;
        this.parcheggio = parcheggio;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        try {
            // Simula la guida prima di arrivare al parcheggio
            int tempoGuida = 2000 + random.nextInt(3000); // 2-5 secondi
            Thread.sleep(tempoGuida);
            
            // Tenta di entrare nel parcheggio
            boolean entrata = parcheggio.entra(this);
            
            if (entrata) {
                // Simula il tempo di permanenza nel parcheggio
                int tempoPermanenza = 3000 + random.nextInt(5000); // 3-8 secondi
                Thread.sleep(tempoPermanenza);
                
                // Esce dal parcheggio
                parcheggio.esci(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
