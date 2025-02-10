class DistributoreBevande {
    private String stato;
    private int credito;

    public DistributoreBevande() {
        this.stato = "ATTESA";
        this.credito = 0;
    }

    public void inserisciMoneta(int importo) {
        if (stato.equals("ATTESA")) {
            credito += importo;
            if (credito >= 100) { // Supponiamo che la bevanda costi 100 unità
                stato = "CREDITO_INSERITO";
            }
        }
    }

    public void selezionaBevanda() {
        if (stato.equals("CREDITO_INSERITO")) {
            stato = "EROGAZIONE";
        }
    }

    public void erogaBevanda() {
        if (stato.equals("EROGAZIONE")) {
            stato = "RESTO";
        }
    }

    public void rilasciaResto() {
        if (stato.equals("RESTO")) {
            credito = 0;
            stato = "ATTESA";
        }
    }

    public String getStato() {
        return stato;
    }
}
