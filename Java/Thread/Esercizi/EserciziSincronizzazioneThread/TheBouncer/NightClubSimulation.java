public class NightClubSimulation {
    public static void main(String[] args) throws InterruptedException {
        final int CLUB_CAPACITY = 5;
        final int MIN_AGE = 18;
        final int NUM_CLIENTS = 10;

        Club club = new Club(CLUB_CAPACITY, MIN_AGE);
        
        System.out.println("=== SIMULAZIONE LOCALE NOTTURNO ===");
        System.out.println("Capacità massima: " + CLUB_CAPACITY);
        System.out.println("Età minima: " + MIN_AGE);
        System.out.println("Numero clienti: " + NUM_CLIENTS);
        System.out.println("-----------------------------------");

        Client[] clients = new Client[NUM_CLIENTS];
        for (int i = 0; i < NUM_CLIENTS; i++) {
            int age = 16 + new Random().nextInt(25); 
            clients[i] = new Client("Cliente-" + (i+1), age, club);
        }

        for (Client client : clients) {
            client.start();
        }

        for (Client client : clients) {
            client.join();
        }

        System.out.println("-----------------------------------");
        System.out.println("=== CHIUSURA DEL LOCALE ===");
    }
}
