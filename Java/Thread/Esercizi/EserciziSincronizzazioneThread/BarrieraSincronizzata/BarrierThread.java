class BarrierThread extends Thread {
    private BarrieraSincronizzata barriera;
    private Random random = new Random();

    public BarrierThread(String nome, BarrieraSincronizzata barriera) {
        super(nome);
        this.barriera = barriera;
    }

    @Override
    public void run() {
        try {
            int tempoLavoro = 2000 + random.nextInt(3000);
            Thread.sleep(tempoLavoro);
            
            System.out.println(getName() + " ha completato il lavoro iniziale e raggiunge la barriera");
            System.out.println("Thread in attesa: " + barriera.getThreadInAttesa());
            
            barriera.attendi();
          
            System.out.println(getName() + " riprende a lavorare");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            System.out.println(getName() + ": Barriera interrotta!");
        }
    }
}
