public class SensoreMovimento extends Sensore {
    private float velocitaMovimento;

    public SensoreMovimento(String idSensore, float velocitaMovimento) {
        super(idSensore, "Movimento");
        this.velocitaMovimento = velocitaMovimento;
    }

    @Override
    public void diagnostica() {
        System.out.println("Diagnostica eseguita su sensore movimento.");
    }

    @Override
    public void calcolaStatistiche() {
        System.out.println("Calcolo delle statistiche sul movimento.");
    }

    public float getVelocitaMovimento() {
        return velocitaMovimento;
    }

    public void setVelocitaMovimento(float velocitaMovimento) {
        this.velocitaMovimento = velocitaMovimento;
    }
}
