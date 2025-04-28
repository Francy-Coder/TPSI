import java.util.concurrent.Semaphore;

class CatenaMontaggio {
    private Semaphore semAssemblaggio = new Semaphore(2);
    private Semaphore semCollaudo = new Semaphore(1);

    public void assembla(String nomeOperaio) throws InterruptedException {
        semAssemblaggio.acquire();
        System.out.println("[" + nomeOperaio + "] inizia assemblaggio");
        
        Thread.sleep(1000 + (int)(Math.random() * 1000));
        
        System.out.println("[" + nomeOperaio + "] finisce assemblaggio");
        semAssemblaggio.release();
        
        collauda(nomeOperaio);
    }

    public void collauda(String nomeOperaio) throws InterruptedException {
        semCollaudo.acquire();
        System.out.println("[" + nomeOperaio + "] inizia collaudo");
        
        Thread.sleep(500 + (int)(Math.random() * 500));
        
        System.out.println("[" + nomeOperaio + "] finisce collaudo");
        semCollaudo.release();
    }
}

class Operaio extends Thread {
    private CatenaMontaggio catena;
    private String nomeOperaio;

    public Operaio(CatenaMontaggio catena, String nomeOperaio) {
        this.catena = catena;
        this.nomeOperaio = nomeOperaio;
    }

    @Override
    public void run() {
        try {
            while (true) {
                catena.assembla(nomeOperaio);
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            System.out.println("[" + nomeOperaio + "] interrotto");
        }
    }
}

public class MainCatenaMontaggio {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_OPERAI = 5;
        
        CatenaMontaggio catena = new CatenaMontaggio();
        Operaio[] operai = new Operaio[NUM_OPERAI];
        
        for (int i = 0; i < NUM_OPERAI; i++) {
            operai[i] = new Operaio(catena, "Operaio" + (i+1));
            operai[i].start();
        }
        
        Thread.sleep(10000);
        
        for (Operaio o : operai) {
            o.interrupt();
        }
    }
}
