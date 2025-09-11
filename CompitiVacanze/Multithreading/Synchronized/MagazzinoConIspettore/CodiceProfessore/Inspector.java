public class Inspector extends Thread {
    private Magazzino wh;
    private long inspectorSleepTime;

    Inspector(Magazzino wh, long inspectorSleepTime) {
        this.wh = wh;
        this.inspectorSleepTime = inspectorSleepTime;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(inspectorSleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Ispettore: ended...");
                return;
            }
            wh.ispezionaMagazzino();
        }
    }

}
