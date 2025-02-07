class Città {
    private Granaio granaio;
    private PortoCommerciale porto;
    private Caserma caserma;

    public Città(int limiteRisorse) {
        granaio = new Granaio(limiteRisorse);
        porto = new PortoCommerciale(granaio);
        caserma = new Caserma(granaio);
    }

    public void simulaGiorno() {
        try {
            granaio.produceRisorse();
            porto.scambioRisorse();
            caserma.addestraSoldati();
            caserma.gestisciEventi();
        } catch (MassimoRisorseSuperatoException | ScarsitàRisorseException | AddestramentoFallitoException |
                 RivoltaAvvenutaException | InvasioneAvvenutaException e) {
            System.out.println(e.getMessage());
        }
    }
}