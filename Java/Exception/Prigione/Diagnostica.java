public class Diagnostica {
    public void eseguiDiagnostica(Sensore sensore) {
        System.out.println("Eseguendo diagnostica su " + sensore.getTipo());
        sensore.diagnostica();
    }
}
