public class AdderThread implements Runnable{

    private final Counter counter;

    public AdderThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            try {
                counter.add(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
