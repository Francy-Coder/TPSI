class ThreadCatenaV2 extends Thread {
    private Thread predecessore;
    private static int ultimoNumero = 0;
    private static final Object lock = new Object();
    private String nome;

    public ThreadCatenaV2(Thread predecessore, String nome) {
        this.predecessore = predecessore;
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            if (predecessore != null) {
                predecessore.join();
            }
            
            synchronized(lock) {
                for (int i = 1; i <= 10; i++) {
                    ultimoNumero++;
                    System.out.println(nome + ": " + ultimoNumero);
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MainCatenaThreadV2 {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        
        Thread[] threads = new Thread[NUM_THREADS];
        Thread predecessore = null;
        
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new ThreadCatenaV2(predecessore, "Thread" + (char)('A' + i));
            threads[i].start();
            predecessore = threads[i];
        }
        
        try {
            threads[NUM_THREADS-1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Tutti i thread hanno terminato");
    }
}
