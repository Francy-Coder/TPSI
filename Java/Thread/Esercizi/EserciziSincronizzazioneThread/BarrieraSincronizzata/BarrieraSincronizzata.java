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
