public class Votante {
    private String nome;
    private int budgetVoti;
    private Canzone canzoneVotata;

    public Votante(String nome, int budgetVoti) {
        this.nome = nome;
        this.budgetVoti = budgetVoti;
        this.canzoneVotata = null;
    }

    public String getNome() {
        return nome;
    }

    public int getBudgetVoti() {
        return budgetVoti;
    }

    public void assegnaVoto(Canzone canzone, int voto) throws VotoInvalidoException, LimiteVotiSuperatoException, CanzoneGiaVotataException {
        if (voto < 1 || voto > 10) {
            throw new VotoInvalidoException("Il voto deve essere compreso tra 1 e 10.");
        }
        if (budgetVoti <= 0) {
            throw new LimiteVotiSuperatoException("Hai esaurito il tuo budget di voti.");
        }
        if (canzoneVotata != null && canzoneVotata.equals(canzone)) {
            throw new CanzoneGiaVotataException("Hai già votato questa canzone.");
        }
        canzone.assegnaVoto(voto);
        budgetVoti--;
        canzoneVotata = canzone;
    }
}
