public class PostazioneRDA {
    private Coordinata posizione;
    private int difesa;
    private int unobtaniumRimanente;

    public PostazioneRDA(Coordinata posizione, int difesa, int unobtaniumRimanente) {
        this.posizione = posizione;
        this.difesa = difesa;
        this.unobtaniumRimanente = unobtaniumRimanente;
    }

    public void difendi(int forzaAttacco) throws DifesaInsufficienteException, UnobtaniumEsauritoException {
        if (unobtaniumRimanente <= 0) {
            throw new UnobtaniumEsauritoException("Unobtanium esaurito.");
        }
        if (forzaAttacco > difesa) {
            throw new DifesaInsufficienteException("Difesa insufficiente.");
        } else {
            difesa -= forzaAttacco;
            unobtaniumRimanente--;
        }
    }

    public Coordinata getPosizione() {
        return posizione;
    }

    public void setPosizione(Coordinata posizione) {
        this.posizione = posizione;
    }

    public int getDifesa() {
        return difesa;
    }

    public void setDifesa(int difesa) {
        this.difesa = difesa;
    }

    public int getUnobtaniumRimanente() {
        return unobtaniumRimanente;
    }

    public void setUnobtaniumRimanente(int unobtaniumRimanente) {
        this.unobtaniumRimanente = unobtaniumRimanente;
    }
}