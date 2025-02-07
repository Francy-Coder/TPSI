public class Prigione {
    private String nome;
    private String indirizzo;
    private SistemaMonitoraggio[] listaSistemi;

    public Prigione(String nome, String indirizzo, int numSistemi) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.listaSistemi = new SistemaMonitoraggio[numSistemi];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public SistemaMonitoraggio[] getListaSistemi() {
        return listaSistemi;
    }

    public void setListaSistemi(SistemaMonitoraggio[] listaSistemi) {
        this.listaSistemi = listaSistemi;
    }
}
