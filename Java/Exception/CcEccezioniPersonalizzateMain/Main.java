public class Main {
    public static void main(String[] args) {
        try {
            SistemaTrasporto sistema = new SistemaTrasporto(10, 5, 5);

            Fermata fermata1 = new Fermata("Stazione Centrale");
            Fermata fermata2 = new Fermata("Duomo");
            sistema.aggiungiFermata(fermata1);
            sistema.aggiungiFermata(fermata2);

            sistema.prenotaTragitto("Stazione Centrale", "Duomo");

            Biglietto biglietto = new Biglietto(true, "2025-12-31");
            sistema.utilizzaBiglietto(biglietto);

            Tragitto tragitto = new Tragitto("Stazione Centrale", "Duomo");
            sistema.annullaTragitto(tragitto);

        } catch (FermataNonTrovataException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (BigliettoScadutoException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (TragittoAnnullatoException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (InputNonValidoException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
