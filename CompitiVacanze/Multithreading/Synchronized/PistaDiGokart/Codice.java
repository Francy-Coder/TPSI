import java.util.Random;
import java.util.concurrent.TimeUnit;

class PistaGokart {
    private final int maxKartPista = 4;
    private final int maxPersoneSpogliatoi = 2;
    private final int giriMassimi = 15;
    
    private int kartInPista = 0;
    private int personeInSpogliatoi = 0;
    
    private final Random random = new Random();

    public synchronized void entraSpogliatoio(String nomePilota) throws InterruptedException {
        while (personeInSpogliatoi >= maxPersoneSpogliatoi) {
            System.out.println(nomePilota + " aspetta: spogliatoi pieni (" + personeInSpogliatoi + "/2)");
            wait();
        }
        
        personeInSpogliatoi++;
        System.out.println(nomePilota + " entra negli spogliatoi (" + personeInSpogliatoi + "/2)");

        int tempoSpogliatoio = random.nextInt(1000) + 500;
        Thread.sleep(tempoSpogliatoio);
        System.out.println(nomePilota + " ha indossato tuta e casco (" + tempoSpogliatoio + "ms)");
    }
    
    public synchronized void esciSpogliatoio(String nomePilota) {
        personeInSpogliatoi--;
        System.out.println(nomePilota + " esce dagli spogliatoi (" + personeInSpogliatoi + "/2)");
        notifyAll();
    }
    
    public synchronized void entraInPista(String nomePilota) throws InterruptedException {
        while (kartInPista >= maxKartPista) {
            System.out.println(nomePilota + " aspetta: pista piena (" + kartInPista + "/4)");
            wait();
        }
        
        kartInPista++;
        System.out.println(nomePilota + " entra in pista (" + kartInPista + "/4)");
    }
    
    public synchronized void esciDallaPista(String nomePilota) {
        kartInPista--;
        System.out.println(nomePilota + " esce dalla pista (" + kartInPista + "/4)");
        notifyAll();
    }

    public void effettuaGiri(String nomePilota) throws InterruptedException {
        System.out.println(nomePilota + " inizia i giri!");
        
        for (int giro = 1; giro <= giriMassimi; giro++) {
            int tempoGiro = random.nextInt(300) + 200;
            Thread.sleep(tempoGiro);
            
            System.out.println(nomePilota + " - Giro " + giro + "/" + giriMassimi + 
                             " (" + tempoGiro + "ms)");
            
            if (random.nextInt(100) < 10) {
                System.out.println(nomePilota + " fa un sorpasso!");
            }
            if (random.nextInt(100) < 5) { 
                System.out.println(nomePilota + " perde un po' di tempo in una curva!");
            }
        }
        
        System.out.println(nomePilota + " HA COMPLETATO TUTTI I " + giriMassimi + " GIRI!");
    }
 
    public synchronized void tornaSpogliatoio(String nomePilota) throws InterruptedException {
        while (personeInSpogliatoi >= maxPersoneSpogliatoi) {
            System.out.println(nomePilota + " aspetta per togliersi tuta e casco (" + personeInSpogliatoi + "/2)");
            wait();
        }
        
        personeInSpogliatoi++;
        System.out.println(nomePilota + " torna negli spogliatoi per cambiarsi (" + personeInSpogliatoi + "/2)");

        int tempoSpogliatoio = random.nextInt(800) + 400;
        Thread.sleep(tempoSpogliatoio);
        System.out.println(nomePilota + " si è cambiato e indossa i propri vestiti (" + tempoSpogliatoio + "ms)");
    }
}

class Pilota extends Thread {
    private final PistaGokart pista;
    private final String nome;
    private final Random random;
    
    public Pilota(PistaGokart pista, String nome) {
        this.pista = pista;
        this.nome = nome;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        try {
            int tempoArrivo = random.nextInt(2000) + 500;
            Thread.sleep(tempoArrivo);
            System.out.println("🚗 " + nome + " arriva alla pista di go-kart");
          
            pista.entraSpogliatoio(nome);
            pista.esciSpogliatoio(nome);
            
            pista.entraInPista(nome);
            
            pista.effettuaGiri(nome);
            
            pista.esciDallaPista(nome);
      
            pista.tornaSpogliatoio(nome);
            pista.esciSpogliatoio(nome);
            
            System.out.println("🎉 " + nome + " ha completato l'esperienza!");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nome + " è stato interrotto durante la gara");
        }
    }
}

public class GaraGokart {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== INIZIO GARA DI GO-KART ===");
        System.out.println("Piloti: 8 amici");
        System.out.println("Massimo in pista: 4 kart");
        System.out.println("Massimo negli spogliatoi: 2 persone");
        System.out.println("Giri per pilota: 15");
        System.out.println("===================================");
        
        PistaGokart pista = new PistaGokart();
        
        String[] nomiPiloti = {
            "Mario Rossi", "Luigi Bianchi", "Giulia Verdi", "Anna Neri",
            "Marco Gialli", "Laura Blu", "Paolo Viola", "Sara Arancioni"
        };
        
        Pilota[] piloti = new Pilota[nomiPiloti.length];
        for (int i = 0; i < nomiPiloti.length; i++) {
            piloti[i] = new Pilota(pista, nomiPiloti[i]);
        }
        
        for (Pilota pilota : piloti) {
            pilota.start();
        }
        
        for (Pilota pilota : piloti) {
            pilota.join();
        }
        
        System.out.println("===================================");
        System.out.println("=== GARA TERMINATA ===");
        System.out.println("Tutti i piloti hanno completato l'esperienza!");
    }
}
