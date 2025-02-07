public class Tragitto {
    private String partenza;
    private String arrivo;
    private boolean annullato;

    public Tragitto(String partenza, String arrivo) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.annullato = false;
    }

    public void annullaTragitto() {
        this.annullato = true;
    }

    public boolean isAnnullato() {
        return annullato;
    }

    public String getPartenza() {
        return partenza;
    }

    public String getArrivo() {
        return arrivo;
    }
}
