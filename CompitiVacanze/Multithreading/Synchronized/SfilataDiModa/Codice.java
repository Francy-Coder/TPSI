import java.util.Random;
import java.util.concurrent.Semaphore;

class SfilataModa {
    private final Semaphore camerini;
    private final Semaphore passerella;
    private final Random random = new Random();
    
    public SfilataModa(int numCamerini, int numPostiPasserella) {
        this.camerini = new Semaphore(numCamerini, true);
        this.passerella = new Semaphore(numPostiPasserella, true);
    }
    
    public void partecipaSfilata(String nomeModello) {
        try {
            System.out.println(nomeModello + " arriva alla sfilata");
            
            System.out.println(nomeModello + " aspetta un camerino libero...");
            camerini.acquire();
            System.out.println(nomeModello + " entra nel camerino");
            
            int tempoCambiarsi = random.nextInt(2000) + 1000;
            System.out.println(nomeModello + " si sta cambiando (" + tempoCambiarsi + "ms)");
            Thread.sleep(tempoCambiarsi);
            System.out.println(nomeModello + " ha finito di cambiarsi");
      
            System.out.println(nomeModello + " aspetta un posto in passerella...");
            passerella.acquire();
            System.out.println(nomeModello + " entra in passerella");
     
            int tempoSfilata = random.nextInt(3000) + 2000;
            System.out.println(nomeModello + " sta sfilando (" + tempoSfilata + "ms)");
            Thread.sleep(tempoSfilata);
            System.out.println(nomeModello + " ha finito di sfilare");
  
            passerella.release();
            System.out.println(nomeModello + " lascia la passerella");
     
            camerini.release();
            System.out.println(nomeModello + " lascia il camerino");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nomeModello + " è stato interrotto");
        }
    }
}

class Modello extends Thread {
    private final SfilataModa sfilata;
    private final String nome;
    
    public Modello(SfilataModa sfilata, String nome) {
        this.sfilata = sfilata;
        this.nome = nome;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                sfilata.partecipaSfilata(nome);   
                Thread.sleep(new Random().nextInt(4000) + 2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class SfilataDiModa {
    public static void main(String[] args) {

        int numCamerini = 3;
        int numPostiPasserella = 2;
        int numModelli = 10;
        
        SfilataModa sfilata = new SfilataModa(numCamerini, numPostiPasserella);

        for (int i = 1; i <= numModelli; i++) {
            String nome = (i % 2 == 0) ? "Modella_" + i : "Modello_" + i;
            new Modello(sfilata, nome).start();
        }
        
        System.out.println("Sfilata di moda iniziata con " + numModelli + " modelli!");
        System.out.println("Camerini disponibili: " + numCamerini);
        System.out.println("Posti in passerella: " + numPostiPasserella);
    }
}
