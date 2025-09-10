package CompitiVacanze.MagazzinoConIspettore;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Produttore> produttori = new ArrayList<>();

        Magazzino magazzino = new Magazzino(10);

        for (int i = 0; i < 20; i++){
            produttori.add(new Produttore(magazzino));
        }

        for(Produttore p : produttori){
            p.start();
        }
    }
}
