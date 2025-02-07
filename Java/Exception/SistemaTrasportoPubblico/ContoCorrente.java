public class ContoCorrente {
    private String nomeCorrentista;
    private double saldo;

    public ContoCorrente(String nomeCorrentista, double saldoIniziale) {
        this.nomeCorrentista = nomeCorrentista;
        this.saldo = saldoIniziale;
    }

    public void preleva(double importo) throws SaldoInsufficienteException, CifraNegativaException {
        if (importo < 0) {
            throw new CifraNegativaException("L'importo del prelievo non può essere negativo.");
        }
        if (importo > saldo) {
            throw new SaldoInsufficienteException("Saldo insufficiente per effettuare il prelievo.");
        }
        saldo -= importo;
    }

    public void deposita(double importo) throws CifraNegativaException {
        if (importo < 0) {
            throw new CifraNegativaException("L'importo del deposito non può essere negativo.");
        }
        saldo += importo;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public String getNomeCorrentista() {
        return nomeCorrentista;
    }
}
