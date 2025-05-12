class Buyer extends Thread {
    private TicketManager ticketManager;

    public Buyer(String name, TicketManager ticketManager) {
        super(name);
        this.ticketManager = ticketManager;
    }

    @Override
    public void run() {
        ticketManager.purchaseTicket(this);
    }
}
