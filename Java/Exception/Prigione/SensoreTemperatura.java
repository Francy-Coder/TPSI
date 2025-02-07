public class SensoreTemperatura extends Sensore {
    private float valoreTemperatura;

    public SensoreTemperatura(String idSensore, float valoreTemperatura) {
        super(idSensore, "Temperatura");
        this.valoreTemperatura = valoreTemperatura;
    }

    @Override
    public void diagnostica() {
        System.out.println("Diagnostica eseguita su sensore temperatura.");
    }

    @Override
    public void calcolaStatistiche() {
        System.out.println("Calcolo delle statistiche della temperatura.");
    }

    public float getValoreTemperatura() {
        return valoreTemperatura;
    }

    public void setValoreTemperatura(float valoreTemperatura) {
        this.valoreTemperatura = valoreTemperatura;
    }
}
