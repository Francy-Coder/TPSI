public class Main {
    public static void main(String[] args) {
        Votazione votazione = new Votazione();

        votazione.aggiungiCanzone(new Canzone("Canzone A", "Artista A"));
        votazione.aggiungiCanzone(new Canzone("Canzone B", "Artista B"));

        votazione.aggiungiVotante(new Votante("Votante1", 3));
        votazione.aggiungiVotante(new Votante("Votante2", 2));

        try {
            votazione.assegnaVoto("Votante1", "Canzone A", 8);
            votazione.assegnaVoto("Votante2", "Canzone B", 9);
            votazione.assegnaVoto("Votante1", "Canzone B", 6);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
