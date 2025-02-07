public class SistemaTrasporto {
    private Fermata[] fermate;
    private Tragitto[] tragitti;
    private int numeroFermate;
    private int numeroTragitti;

    public SistemaTrasporto(int maxFermate, int maxBiglietti, int maxTragitti) {
        this.fermate = new Fermata[maxFermate];
        this.tragitti = new Tragitto[maxTragitti];
        this.numeroFermate = 0;
        this.numeroTragitti = 0;
    }

    public void aggiungiFermata(Fermata fermata) {
        if (numeroFermate < fermate.length) {
            fermate[numeroFermate++] = fermata;
            System.out.println("Fermata aggiunta: " + fermata.getNome());
        } else {
            System.out.println("Impossibile aggiungere più fermate, capacità raggiunta.");
        }
    }

    public void prenotaTragitto(String partenza, String arrivo) throws FermataNonTrovataException, InputNonValidoException {
        if (partenza == null || arrivo == null || partenza.isEmpty() || arrivo.isEmpty()) {
            throw new InputNonValidoException("L'input fornito non è valido.");
        }

        Fermata fermataPartenza = trovaFermata(partenza);
        Fermata fermataArrivo = trovaFermata(arrivo);

        if (fermataPartenza == null || fermataArrivo == null) {
            throw new FermataNonTrovataException("Una delle fermate non esiste.");
        }

        if (numeroTragitti < tragitti.length) {
            Tragitto tragitto = new Tragitto(partenza, arrivo);
            tragitti[numeroTragitti++] = tragitto;
            System.out.println("Tragitto prenotato: " + partenza + " -> " + arrivo);
        } else {
            System.out.println("Impossibile prenotare più tragitti, capacità raggiunta.");
        }
    }

    public Fermata trovaFermata(String nomeFermata) {
        for (int i = 0; i < numeroFermate; i++) {
            if (fermate[i].getNome().equals(nomeFermata)) {
                return fermate[i];
            }
        }
        return null;
    }

    public void utilizzaBiglietto(Biglietto biglietto) throws BigliettoScadutoException {
        if (!biglietto.isValido()) {
            throw new BigliettoScadutoException("Il biglietto è scaduto.");
        }
        System.out.println("Biglietto valido utilizzato.");
    }

    public void annullaTragitto(Tragitto tragitto) {
        for (int i = 0; i < numeroTragitti; i++) {
            if (tragitti[i] == tragitto) {
                tragitti[i].annullaTragitto();
                System.out.println("Tragitto annullato: " + tragitto.getPartenza() + " -> " + tragitto.getArrivo());
                return;
            }
        }
        throw new TragittoAnnullatoException("Il tragitto è stato annullato in modo imprevisto.");
    }
}
