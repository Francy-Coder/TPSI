class Semaforo {
    private String stato;

    public Semaforo() {
        this.stato = "ROSSO";
    }

    public void cambiaStato() {
        switch (stato) {
            case "ROSSO":
                stato = "GIALLO";
                break;
            case "GIALLO":
                stato = "VERDE";
                break;
            case "VERDE":
                stato = "ROSSO";
                break;
        }
    }

    public String getStato() {
        return stato;
    }
}
