import java.util.concurrent.atomic.AtomicInteger;

class TicketManager {
    private AtomicInteger availableTickets;

    public TicketManager(int initialTickets) {
        this.availableTickets = new AtomicInteger(initialTickets);
    }

    public synchronized boolean purchaseTicket(Buyer buyer) {
        if (availableTickets.get() > 0) {
            // Simula un piccolo ritardo nell'acquisto per mostrare la concorrenza
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            int remaining = availableTickets.decrementAndGet();
            System.out.println(buyer.getName() + " ha acquistato un biglietto. Biglietti rimanenti: " + remaining);
            return true;
        } else {
            System.out.println(buyer.getName() + " non ha potuto acquistare un biglietto. Esauriti!");
            return false;
        }
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }
}



public class OnlineTicketSystem {
    public static void main(String[] args) {
        final int TOTAL_TICKETS = 10;
        final int TOTAL_BUYERS = 20;

        TicketManager ticketManager = new TicketManager(TOTAL_TICKETS);
        
        System.out.println("Sistema di vendita biglietti online");
        System.out.println("Biglietti disponibili: " + ticketManager.getAvailableTickets());
        System.out.println("Numero di acquirenti: " + TOTAL_BUYERS);
        System.out.println("-----------------------------------");

        // Creazione e avvio degli acquirenti
        for (int i = 1; i <= TOTAL_BUYERS; i++) {
            new Buyer("Acquirente-" + i, ticketManager).start();
        }
    }
}
