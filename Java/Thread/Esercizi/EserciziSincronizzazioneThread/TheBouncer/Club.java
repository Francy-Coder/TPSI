import java.util.Random;
import java.util.concurrent.Semaphore;

class Club {
    private Semaphore capacity;
    private final int maxCapacity;
    private final int minAge;
    private Random random = new Random();

    public Club(int maxCapacity, int minAge) {
        this.maxCapacity = maxCapacity;
        this.minAge = minAge;
        this.capacity = new Semaphore(maxCapacity, true);
    }

    public boolean tryEnter(Client client) throws InterruptedException {
        if (client.getAge() < minAge) {
            System.out.println(client.getName() + " ha solo " + client.getAge() + " anni. INGRESSO NEGATO!");
            return false;
        }

        System.out.println(client.getName() + " (" + client.getAge() + " anni) sta cercando di entrare...");

        if (capacity.tryAcquire()) {
            System.out.println(client.getName() + " è entrato nel club. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
            return true;
        }

        System.out.println(client.getName() + " deve aspettare, il club è pieno...");
        capacity.acquire();
        System.out.println(client.getName() + " finalmente entra nel club dopo l'attesa. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
        return true;
    }

    public void exit(Client client) {
        capacity.release();
        System.out.println(client.getName() + " è uscito dal club. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
    }

    public void partyTime(Client client) throws InterruptedException {
        int partyTime = 3000 + random.nextInt(7000); 
        Thread.sleep(partyTime);
    }
}

