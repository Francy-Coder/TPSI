import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BarrieraSincronizzata {
    private CyclicBarrier barriera;

    public BarrieraSincronizzata(int soglia) {
        this.barriera = new CyclicBarrier(soglia, () -> {
            System.out.println("\n=== SOGLIA RAGGIUNTA! Tutti i thread possono proseguire ===\n");
        });
    }

    public void attendi() throws InterruptedException, BrokenBarrierException {
        barriera.await();
    }

    public int getThreadInAttesa() {
        return barriera.getParties() - barriera.getNumberWaiting();
    }
}

class BarrierThread extends Thread {
    private BarrieraSincronizzata barriera;
    private Random random = new Random();

    public BarrierThread(String nome, BarrieraSincronizzata barriera) {
        super(nome);
        this.barriera = barriera;
    }

    @Override
    public void run() {
        try {
            // Simula lavoro prima della barriera (2-5 secondi)
            int tempoLavoro = 2000 + random.nextInt(3000);
            Thread.sleep(tempoLavoro);
            
            System.out.println(getName() + " ha completato il lavoro iniziale e raggiunge la barriera");
            System.out.println("Thread in attesa: " + barriera.getThreadInAttesa());
            
            // Attende alla barriera
            barriera.attendi();
            
            // Riprende dopo la barriera
            System.out.println(getName() + " riprende a lavorare");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            System.out.println(getName() + ": Barriera interrotta!");
        }
    }
}
