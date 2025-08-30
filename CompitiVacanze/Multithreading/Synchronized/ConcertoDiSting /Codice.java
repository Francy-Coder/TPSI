import java.util.Random;

class BotteghinoConcerto {
    private int bigliettiDisponibili;
    private final int venditoriDisponibili;
    private int venditoriOccupati = 0;
    
    public BotteghinoConcerto(int bigliettiTotali, int numVenditori) {
        this.bigliettiDisponibili = bigliettiTotali;
        this.venditoriDisponibili = numVenditori;
    }
    
    public synchronized boolean compraBiglietto(String nomeCompratore) {
        while (venditoriOccupati >= venditoriDisponibili) {
            try {
                System.out.println(nomeCompratore + " aspetta: tutti i venditori sono occupati");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        if (bigliettiDisponibili <= 0) {
            System.out.println(nomeCompratore + ": SOLD OUT! Non ci sono più biglietti");
            return false;
        }

        venditoriOccupati++;
        System.out.println(nomeCompratore + " sta acquistando (Venditori: " + venditoriOccupati + "/" + venditoriDisponibili + 
                          ", Biglietti: " + bigliettiDisponibili + ")");

        try {
            Thread.sleep(new Random().nextInt(500) + 300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            venditoriOccupati--;
            notifyAll();
            return false;
        }
  
        bigliettiDisponibili--;
        System.out.println(nomeCompratore + " ha comprato un biglietto! Biglietti rimasti: " + bigliettiDisponibili);

        venditoriOccupati--;
        notifyAll();
        
        return true;
    }
    
    public synchronized int getBigliettiDisponibili() {
        return bigliettiDisponibili;
    }
}

class Compratore extends Thread {
    private final BotteghinoConcerto botteghino;
    private final String nome;
    private final Random random;
    private boolean bigliettoAcquistato = false;
    
    public Compratore(BotteghinoConcerto botteghino, String nome) {
        this.botteghino = botteghino;
        this.nome = nome;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        try {
            int attesaIniziale = random.nextInt(2000) + 500;
            Thread.sleep(attesaIniziale);
            
            System.out.println(nome + " arriva al botteghino");
      
            bigliettoAcquistato = botteghino.compraBiglietto(nome);
            
            if (bigliettoAcquistato) {
                System.out.println(nome + " è felice! Ha il biglietto per il concerto!");
            } else if (botteghino.getBigliettiDisponibili() <= 0) {
                System.out.println(nome + " è triste: concerto sold out!");
            } else {
                System.out.println(nome + " rinuncia dopo aver atteso troppo");
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nome + " è stato interrotto");
        }
    }
    
    public boolean haBiglietto() {
        return bigliettoAcquistato;
    }
}

public class ConcertoDiSting {
    public static void main(String[] args) throws InterruptedException {
        int numeroPersone = 200;
        int bigliettiDisponibili = 100;
        int numeroVenditori = 5;
        
        BotteghinoConcerto botteghino = new BotteghinoConcerto(bigliettiDisponibili, numeroVenditori);
        
        System.out.println("=== CONCERTO DI STING ===");
        System.out.println("Biglietti disponibili: " + bigliettiDisponibili);
        System.out.println("Venditori: " + numeroVenditori);
        System.out.println("Persone in coda: " + numeroPersone);
        System.out.println("=========================");
     
        Compratore[] compratori = new Compratore[numeroPersone];
        for (int i = 0; i < numeroPersone; i++) {
            compratori[i] = new Compratore(botteghino, "Persona_" + (i + 1));
        }
      
        for (Compratore compratore : compratori) {
            compratore.start();
        }
 
        for (Compratore compratore : compratori) {
            compratore.join();
        }

        int bigliettiVenduti = bigliettiDisponibili - botteghino.getBigliettiDisponibili();
        int personeSenzaBiglietto = numeroPersone - bigliettiVenduti;
        
        System.out.println("\n=== RISULTATO FINALE ===");
        System.out.println("Biglietti venduti: " + bigliettiVenduti + "/" + bigliettiDisponibili);
        System.out.println("Persone senza biglietto: " + personeSenzaBiglietto);
        System.out.println("Persone totali: " + numeroPersone);
   
        int successi = 0;
        for (Compratore compratore : compratori) {
            if (compratore.haBiglietto()) {
                successi++;
            }
        }
        System.out.println("Acquisti confermati: " + successi);
    }
}
