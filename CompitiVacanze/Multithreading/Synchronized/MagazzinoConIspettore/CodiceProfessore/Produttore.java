package CompitiVacanze.MagazzinoConIspettore;

import java.util.Random;

public class Produttore extends Thread{

    public Magazzino magazzino;
    private Random rnd;

    public Produttore(Magazzino magazzino){
        this.magazzino = magazzino;
        this.rnd = new Random();
    }

    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()) {
            int value = new Random().nextInt(10, 100);
            try {
                magazzino.inserisciNumero(value);
                Thread.sleep(200 + new Random().nextInt(300));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Prod exiting...");
    }
}
