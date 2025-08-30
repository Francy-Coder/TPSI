import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Magazzino {
    private final int capacita;
    private final List<Integer> contenuto;
    private boolean ispettoreInAccesso = false;
    private final int X;

    public Magazzino(int capacita, int X) {
        this.capacita = capacita;
        this.X = X;
        this.contenuto = new ArrayList<>();
    }

    public synchronized void metti(int numero) throws InterruptedException {
        while (contenuto.size() >= capacita || ispettoreInAccesso) {
            wait();
        }
        
        contenuto.add(numero);
        System.out.println("Prodotto " + numero + " inserito. Magazzino: " + contenuto);
        notifyAll();
    }

    public synchronized int consuma() throws InterruptedException {
        while (contenuto.isEmpty() || ispettoreInAccesso) {
            wait();
        }
        
        int ultimo = contenuto.remove(contenuto.size() - 1);
        System.out.println("Prodotto " + ultimo + " consumato. Magazzino: " + contenuto);
        notifyAll();
        return ultimo;
    }

    public synchronized void ispeziona() throws InterruptedException {
        while (ispettoreInAccesso || !contenuto.isEmpty()) {
            wait();
        }
        
        ispettoreInAccesso = true;
        notifyAll();
        
        try {
            int somma = 0;
            for (int numero : contenuto) {
                somma += numero;
            }
            
            if (somma >= X) {
                System.out.println("ISPETTORE: Somma " + somma + " >= " + X + ". Reset magazzino.");
                contenuto.clear();
            } else {
                if (!contenuto.isEmpty()) {
                    contenuto.set(0, somma);
                    System.out.println("ISPETTORE: Somma " + somma + " < " + X + ". Primo slot impostato a " + somma);
                }
            }
        } finally {
            ispettoreInAccesso = false;
            notifyAll();
        }
    }

    public synchronized int getSize() {
        return contenuto.size();
    }
}

class Produttore extends Thread {
    private final Magazzino magazzino;
    private final Random random = new Random();

    public Produttore(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int numero = random.nextInt(100) * 2;
                magazzino.metti(numero);
                Thread.sleep(random.nextInt(500) + 100); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumatore extends Thread {
    private final Magazzino magazzino;
    private final Random random = new Random();

    public Consumatore(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            while (true) {
                magazzino.consuma();
                Thread.sleep(random.nextInt(500) + 100); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Ispettore extends Thread {
    private final Magazzino magazzino;

    public Ispettore(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000); 
                magazzino.ispeziona();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MagazzinoConIspettore {
    public static void main(String[] args) {
        int N = 5; 
        int P = 8; 
        int X = 100; 
        
        if (P <= N) {
            System.err.println("ERRORE: Il numero di produttori P deve essere > della capacità N");
            return;
        }
        
        Magazzino magazzino = new Magazzino(N, X);
      
        for (int i = 0; i < P; i++) {
            new Produttore(magazzino).start();
        }
     
        for (int i = 0; i < P; i++) {
            new Consumatore(magazzino).start();
        }
  
        new Ispettore(magazzino).start();
        
        System.out.println("Simulazione avviata con " + P + " produttori, capacità " + N + ", soglia X=" + X);
    }
}
