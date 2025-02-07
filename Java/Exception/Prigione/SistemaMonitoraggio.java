public class SistemaMonitoraggio {
    private String tipo;
    private String id;
    private String nomeSistema;
    private Sensore[] listaSensori;
    private Statistiche statistiche;

    public SistemaMonitoraggio(String tipo, String id, String nomeSistema, int numSensori) {
        this.tipo = tipo;
        this.id = id;
        this.nomeSistema = nomeSistema;
        this.listaSensori = new Sensore[numSensori];
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeSistema() {
        return nomeSistema;
    }

    public void setNomeSistema(String nomeSistema) {
        this.nomeSistema = nomeSistema;
    }

    public Sensore[] getListaSensori() {
        return listaSensori;
    }

    public void setListaSensori(Sensore[] listaSensori) {
        this.listaSensori = listaSensori;
    }

    public Statistiche getStatistiche() {
        return statistiche;
    }

    public void setStatistiche(Statistiche statistiche) {
        this.statistiche = statistiche;
    }
}
