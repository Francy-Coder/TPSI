public class ProceduraAccensioneSpegnimento {
    public void accendi(Sensore sensore) {
        System.out.println("Accensione sensore " + sensore.getTipo());
    }

    public void spegni(Sensore sensore) {
        System.out.println("Spegnimento sensore " + sensore.getTipo());
    }
}
