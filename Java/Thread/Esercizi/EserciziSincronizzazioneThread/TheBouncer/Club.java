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
        // Controllo dell'età
        if (client.getAge() < minAge) {
            System.out.println(client.getName() + " ha solo " + client.getAge() + " anni. INGRESSO NEGATO!");
            return false;
        }

        System.out.println(client.getName() + " (" + client.getAge() + " anni) sta cercando di entrare...");

        // Prova ad acquisire un permesso (non bloccante)
        if (capacity.tryAcquire()) {
            System.out.println(client.getName() + " è entrato nel club. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
            return true;
        }

        // Se il club è pieno, aspetta
        System.out.println(client.getName() + " deve aspettare, il club è pieno...");
        capacity.acquire(); // Aspetta finché non si libera un posto
        System.out.println(client.getName() + " finalmente entra nel club dopo l'attesa. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
        return true;
    }

    public void exit(Client client) {
        capacity.release();
        System.out.println(client.getName() + " è uscito dal club. Clienti presenti: " + (maxCapacity - capacity.availablePermits()));
    }

    public void partyTime(Client client) throws InterruptedException {
        int partyTime = 3000 + random.nextInt(7000); // 3-10 secondi
        Thread.sleep(partyTime);
    }
}

class Client extends Thread {
    private Club club;
    private int age;
    private String name;

    public Client(String name, int age, Club club) {
        super(name);
        this.name = name;
        this.age = age;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void run() {
        try {
            // Simula il tempo per arrivare al club
            Thread.sleep(new Random().nextInt(3000));

            boolean entered = club.tryEnter(this);
            
            if (entered) {
                // Goditi il club
                club.partyTime(this);
                
                // Esci dal club
                club.exit(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

