public class Biglietto {
    private boolean valido;
    private String dataScadenza;

    public Biglietto(boolean valido, String dataScadenza) {
        this.valido = valido;
        this.dataScadenza = dataScadenza;
    }

    public boolean isValido() {
        return valido;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }
}
