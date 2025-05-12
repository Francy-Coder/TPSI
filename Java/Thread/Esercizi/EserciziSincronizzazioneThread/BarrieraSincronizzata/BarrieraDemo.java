public class BarrieraDemo {
    public static void main(String[] args) {
        final int SOGLIA = 3;
        final int NUM_THREADS = 6;

        BarrieraSincronizzata barriera = new BarrieraSincronizzata(SOGLIA);
        
        System.out.println("=== Simulazione Barriera Sincronizzata ===");
        System.out.println("Soglia della barriera: " + SOGLIA);
        System.out.println("Numero di thread: " + NUM_THREADS);
        System.out.println("------------------------------------------");

        for (int i = 1; i <= NUM_THREADS; i++) {
            new BarrierThread("Thread-" + i, barriera).start();
        }
    }
}
