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
            Thread.sleep(new Random().nextInt(3000));

            boolean entered = club.tryEnter(this);
            
            if (entered) {
                club.partyTime(this);
                
                club.exit(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
