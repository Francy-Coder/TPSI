class ThreadCatena extends Thread {
    private Thread predecessore;
    private int x;
    private int y;
    private String nome;

    public ThreadCatena(Thread predecessore, int x, int y, String nome) {
        this.predecessore = predecessore;
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            if (predecessore != null) {
                predecessore.join();
            }
            
            for (int i = x; i <= y; i++) {
                System.out.println(nome + ": " + i);
                Thread.sleep(100); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MainCatenaThread {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int NUMERI_PER_THREAD = 10;
        
        Thread[] threads = new Thread[NUM_THREADS];
        Thread predecessore = null;
        
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * NUMERI_PER_THREAD + 1;
            int end = start + NUMERI_PER_THREAD - 1;
            threads[i] = new ThreadCatena(predecessore, start, end, "Thread" + (char)('A' + i));
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
