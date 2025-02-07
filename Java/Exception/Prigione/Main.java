public class Main {
    public static void main(String[] args) {
        Prigione prigione = new Prigione("Prigione di Stato", "Via delle Prigioni 123", 1);

        SistemaMonitoraggio sistemaTemperatura = new SistemaMonitoraggio("Temperatura", "001", "Sistema Temperatura", 2);

        SensoreTemperatura sensore1 = new SensoreTemperatura("T001", 22.5f);
        SensoreTemperatura sensore2 = new SensoreTemperatura("T002", 23.0f);

        sistemaTemperatura.setListaSensori(new Sensore[] { sensore1, sensore2 });

        SistemaMonitoraggio sistemaMovimento = new SistemaMonitoraggio("Movimento", "002", "Sistema Movimento", 1);

        SensoreMovimento sensore3 = new SensoreMovimento("M001", 5.0f);


        sistemaMovimento.setListaSensori(new Sensore[] { sensore3 });

        prigione.setListaSistemi(new SistemaMonitoraggio[] { sistemaTemperatura, sistemaMovimento });

        Diagnostica diagnostica = new Diagnostica();
        diagnostica.eseguiDiagnostica(sensore1);
        diagnostica.eseguiDiagnostica(sensore2);
        diagnostica.eseguiDiagnostica(sensore3);

        ProceduraAccensioneSpegnimento procedura = new ProceduraAccensioneSpegnimento();
        procedura.accendi(sensore1);
        procedura.spegni(sensore2);
        procedura.accendi(sensore3);

        Statistiche statisticheTemperatura = new Statistiche(2);
        statisticheTemperatura.setDatiStatistiche(new float[] { sensore1.getValoreTemperatura(), sensore2.getValoreTemperatura() });
        System.out.println("Media Temperatura: " + statisticheTemperatura.calcola() + "°C");

        Statistiche statisticheMovimento = new Statistiche(1);
        statisticheMovimento.setDatiStatistiche(new float[] { sensore3.getVelocitaMovimento() });
        System.out.println("Velocità Media Movimento: " + statisticheMovimento.calcola() + " m/s");

        System.out.println("\nSistemi di Monitoraggio nella " + prigione.getNome());
        for (SistemaMonitoraggio sistema : prigione.getListaSistemi()) {
            System.out.println("Sistema Monitoraggio: " + sistema.getNomeSistema());
            for (Sensore sensore : sistema.getListaSensori()) {
                System.out.println("Sensore ID: " + sensore.getIdSensore() + ", Tipo: " + sensore.getTipo());
            }
        }
    }
}