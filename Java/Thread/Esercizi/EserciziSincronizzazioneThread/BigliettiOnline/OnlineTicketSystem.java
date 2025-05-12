public class OnlineTicketSystem {
    public static void main(String[] args) {
        final int TOTAL_TICKETS = 10;
        final int TOTAL_BUYERS = 20;

        TicketManager ticketManager = new TicketManager(TOTAL_TICKETS);
        
        System.out.println("Sistema di vendita biglietti online");
        System.out.println("Biglietti disponibili: " + ticketManager.getAvailableTickets());
        System.out.println("Numero di acquirenti: " + TOTAL_BUYERS);
        System.out.println("-----------------------------------");

        for (int i = 1; i <= TOTAL_BUYERS; i++) {
            new Buyer("Acquirente-" + i, ticketManager).start();
        }
    }
}
