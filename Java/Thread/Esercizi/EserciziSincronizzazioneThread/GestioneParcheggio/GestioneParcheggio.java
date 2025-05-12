public class GestioneParcheggio {
    public static void main(String[] args) {
        final int POSTI_TOTALI = 5;
        final int NUMERO_AUTO = 10;

        Parcheggio parcheggio = new Parcheggio(POSTI_TOTALI);
        
        System.out.println("=== Gestione Parcheggio ===");
        System.out.println("Posti totali: " + POSTI_TOTALI);
        System.out.println("Auto in arrivo: " + NUMERO_AUTO);
        System.out.println("--------------------------");

        for (int i = 1; i <= NUMERO_AUTO; i++) {
            new Auto("Auto-" + i, parcheggio).start();
        }
    }
}
