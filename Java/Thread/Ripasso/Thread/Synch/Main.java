import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();

        Counter c = new Counter();

        for(int i = 0; i < 5; i++){
            threads.add(new Thread(new AdderThread(c)));
        }

        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads){
            t.join();
        }

        System.out.println(c.getCounter());
    }
}