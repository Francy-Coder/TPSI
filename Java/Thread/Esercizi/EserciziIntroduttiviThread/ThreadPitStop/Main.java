public class Main {
    public static void main(String[] args) {
        Thread auto1 = new Thread(new AutoDaCorsa("🏎️ Auto Rossa"));
        Thread auto2 = new Thread(new AutoDaCorsa("🚗 Auto Blu"));
        Thread auto3 = new Thread(new AutoDaCorsa("🚙 Auto Verde"));

        auto1.start();
        auto2.start();
        auto3.start();

        // Stoppo la gara dopo 10 secondi
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        auto1.interrupt();
        auto2.interrupt();
        auto3.interrupt();
    }
}
