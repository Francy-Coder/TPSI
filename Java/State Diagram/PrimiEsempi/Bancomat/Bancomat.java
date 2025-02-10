class Bancomat {
    private String stato;

    public Bancomat() {
        this.stato = "IDLE";
    }

    public void inserisciCarta() {
        if (stato.equals("IDLE")) {
            stato = "CARTA_INSERITA";
        }
    }

    public void inserisciPIN(boolean corretto) {
        if (stato.equals("CARTA_INSERITA")) {
            if (corretto) {
                stato = "PIN_INSERITO";
            } else {
                stato = "IDLE";
            }
        }
    }

    public void selezionaOperazione(String operazione) {
        if (stato.equals("PIN_INSERITO")) {
            switch (operazione) {
                case "prelievo":
                    stato = "PRELIEVO";
                    break;
                case "versamento":
                    stato = "VERSAMENTO";
                    break;
                case "saldo":
                    stato = "CONTROLLO_SALDO";
                    break;
            }
        }
    }

    public void eseguiOperazione() {
        if (stato.equals("PRELIEVO") || stato.equals("VERSAMENTO") || stato.equals("CONTROLLO_SALDO")) {
            stato = "ESPULSIONE_CARTA";
        }
    }

    public void espelliCarta() {
        if (stato.equals("ESPULSIONE_CARTA")) {
            stato = "IDLE";
        }
    }

    public String getStato() {
        return stato;
    }
}
