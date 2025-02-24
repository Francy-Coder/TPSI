import java.util.ArrayList;
import java.util.List;

public class Votazione {
    private List<Canzone> canzoni;
    private List<Votante> votanti;

    public Votazione() {
        this.canzoni = new ArrayList<>();
        this.votanti = new ArrayList<>();
    }

    public void aggiungiCanzone(Canzone canzone) {
        canzoni.add(canzone);
    }

    public void aggiungiVotante(Votante votante) {
        votanti.add(votante);
    }

    public void assegnaVoto(String nomeVotante, String titoloCanzone, int voto) throws CanzoneNonTrovataException, LimiteVotiSuperatoException, VotoInvalidoException, CanzoneGiaVotataException {
        Votante votante = null;
        Canzone canzone = null;

        for (Votante v : votanti) {
            if (v.getNome().equals(nomeVotante)) {
                votante = v;
                break;
            }
        }

        for (Canzone c : canzoni) {
            if (c.getTitolo().equals(titoloCanzone)) {
                canzone = c;
                break;
            }
        }

        if (canzone == null) {
            throw new CanzoneNonTrovataException("Canzone " + titoloCanzone + " non trovata.");
        }
        if (votante == null) {
            throw new LimiteVotiSuperatoException("Votante " + nomeVotante + " non trovato o limite di voti superato.");
        }

        votante.assegnaVoto(canzone, voto);
    }
}
