public class Main {
    public static void main(String[] args) {
        try {
            ContoCorrente conto = new ContoCorrente("Giovanni Rossi", 1000.0);

            System.out.println("Saldo iniziale: " + conto.getSaldo());

            conto.deposita(500.0);
            System.out.println("Saldo dopo deposito di 500: " + conto.getSaldo());

            conto.preleva(200.0);
            System.out.println("Saldo dopo prelievo di 200: " + conto.getSaldo());

            conto.preleva(1500.0);
        } catch (SaldoInsufficienteException | CifraNegativaException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
