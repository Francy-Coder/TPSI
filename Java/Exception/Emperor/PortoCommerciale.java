import java.util.Random;

class PortoCommerciale {
    private Granaio granaio;

    public PortoCommerciale(Granaio granaio) {
        this.granaio = granaio;
    }

    public void scambioRisorse() throws ScarsitàRisorseException {
        Random random = new Random();
        int risorseDaInviare = random.nextInt(20); 
        int risorseDaRicevere = random.nextInt(20); 

        granaio.consumaRisorse(risorseDaInviare);
        granaio.setRisorse(granaio.getRisorse() + risorseDaRicevere);

        System.out.println("Porto ha inviato " + risorseDaInviare + " risorse e ricevuto " + risorseDaRicevere + " risorse.");
    }
}