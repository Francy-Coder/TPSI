public class AutoDaCorsa implements Runnable {
    private String nome;
    private int velocita = 0; 
    private static final int PIT_STOP_INTERVAL = 5; 
    private static final int PIT_STOP_DURATA = 2000; 

    public AutoDaCorsa(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        int iterazioni = 0;
        while (true) {
            velocita += 20;
            iterazioni++;

            System.out.println(nome + " - Velocità attuale: " + velocita + " km/h");

            if (iterazioni % PIT_STOP_INTERVAL == 0) {
                System.out.println(nome + " entra ai box per un pit stop! 🏁");
                try {
                    Thread.sleep(PIT_STOP_DURATA);
                } catch (InterruptedException e) {
                    System.out.println(nome + " ha terminato la gara!");
                    break;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(nome + " ha terminato la gara!");
                break;
            }
        }
    }
}
