import java.util.concurrent.atomic.AtomicInteger;

class TicketManager {
    private AtomicInteger availableTickets;

    public TicketManager(int initialTickets) {
        this.availableTickets = new AtomicInteger(initialTickets);
    }

    public synchronized boolean purchaseTicket(Buyer buyer) {
        if (availableTickets.get() > 0) {
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
