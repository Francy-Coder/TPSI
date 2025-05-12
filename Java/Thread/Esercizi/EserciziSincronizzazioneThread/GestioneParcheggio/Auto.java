class Auto extends Thread {
    private Parcheggio parcheggio;
    private Random random = new Random();
    private String nome;

    public Auto(String nome, Parcheggio parcheggio) {
        super(nome);
        this.nome = nome;
        this.parcheggio = parcheggio;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        try {
            int tempoGuida = 2000 + random.nextInt(3000); 
            Thread.sleep(tempoGuida);
            boolean entrata = parcheggio.entra(this);
            
            if (entrata) {
                int tempoPermanenza = 3000 + random.nextInt(5000);
                Thread.sleep(tempoPermanenza);
                
                parcheggio.esci(this);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
