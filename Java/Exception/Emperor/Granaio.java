import java.util.Random;

class Granaio {
    private int risorse;
    private final int limite;

    public Granaio(int limite) {
        this.limite = limite;
        this.risorse = 0;
    }

    public void produceRisorse() throws MassimoRisorseSuperatoException {
        Random random = new Random();
        int nuoveRisorse = random.nextInt(50); 
        if (risorse + nuoveRisorse > limite) {
            throw new MassimoRisorseSuperatoException("Limite massimo di risorse superato!");
        }
        risorse += nuoveRisorse;
        System.out.println("Granaio ha prodotto " + nuoveRisorse + " risorse. Totale: " + risorse);
    }

    public void consumaRisorse(int quantita) throws ScarsitàRisorseException {
        if (risorse < quantita) {
            throw new ScarsitàRisorseException("Risorse insufficienti!");
        }
        risorse -= quantita;
        System.out.println("Granaio ha consumato " + quantita + " risorse. Totale: " + risorse);
    }

    public int getRisorse() {
        return risorse;
    }

    public void setRisorse(int risorse) {
        this.risorse = risorse;
    }
}