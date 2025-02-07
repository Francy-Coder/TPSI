import java.util.Random;

class Caserma {
    private int soldati;
    private Granaio granaio;

    public Caserma(Granaio granaio) {
        this.granaio = granaio;
        this.soldati = 0;
    }

    public void addestraSoldati() throws AddestramentoFallitoException, ScarsitàRisorseException {
        Random random = new Random();
        int nuoviSoldati = random.nextInt(10); 
        int costo = nuoviSoldati * 5;

        granaio.consumaRisorse(costo);
        soldati += nuoviSoldati;
        System.out.println("Caserma ha addestrato " + nuoviSoldati + " soldati. Totale: " + soldati);
    }

    public void gestisciEventi() throws RivoltaAvvenutaException, InvasioneAvvenutaException {
        Random random = new Random();
        if (random.nextInt(100) < 40) { 
            if (soldati >= 20) {
                soldati -= 20;
                System.out.println("La rivolta è stata sedata con successo.");
            } else {
                granaio.setRisorse(0);
                soldati /= 2;
                throw new RivoltaAvvenutaException("Rivolta avvenuta! Le risorse sono state razziate e il numero dei soldati dimezzato.");
            }
        }

        if (random.nextInt(100) < 20) { 
            if (soldati >= 40) {
                soldati -= 40;
                System.out.println("L'invasione barbarica è stata respinta con successo.");
            } else {
                granaio.setRisorse(0);
                soldati = 0;
                throw new InvasioneAvvenutaException("Invasione barbarica avvenuta! Le risorse sono state razziate e tutti i soldati sono stati uccisi.");
            }
        }
    }

    public int getSoldati() {
        return soldati;
    }
}