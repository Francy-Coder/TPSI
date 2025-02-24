public class Canzone {
    private String titolo;
    private String artista;
    private int voto;

    public Canzone(String titolo, String artista) {
        this.titolo = titolo;
        this.artista = artista;
        this.voto = 0;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getArtista() {
        return artista;
    }

    public int getVoto() {
        return voto;
    }

    public void assegnaVoto(int voto) throws VotoInvalidoException, CanzoneGiaVotataException {
        if (voto < 1 || voto > 10) {
            throw new VotoInvalidoException("Il voto deve essere compreso tra 1 e 10.");
        }
        if (this.voto != 0) {
            throw new CanzoneGiaVotataException("La canzone ha già ricevuto un voto.");
        }
        this.voto = voto;
    }
}
