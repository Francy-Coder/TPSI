//t1.run(); < Sbagliato, perchè non si entra nella scheduler, quindi non è in concorrenza

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        //Creare N Thread che vanno a lavorare sul
        Counter c = new Counter();
        
        ArrayList<Thread> threads = new ArrayList<>();
        
        //Creo N threads
        for(int i = 0; i < 4; i++){
            threads.add(new CounterThread(c));
        }
        
        //Li faccio partire
        for (Thread t : threads) t.start();
    }
}
