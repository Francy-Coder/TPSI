public abstract class Sensore {
    private String idSensore;
    private String tipo;
    private String stato;
    private String dataMisurazione;

    public Sensore(String idSensore, String tipo) {
        this.idSensore = idSensore;
        this.tipo = tipo;
    }

    public abstract void diagnostica();
    public abstract void calcolaStatistiche();

    public String getIdSensore() {
        return idSensore;
    }

    public void setIdSensore(String idSensore) {
        this.idSensore = idSensore;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDataMisurazione() {
        return dataMisurazione;
    }

    public void setDataMisurazione(String dataMisurazione) {
        this.dataMisurazione = dataMisurazione;
    }
}
