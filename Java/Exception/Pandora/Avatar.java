public class Avatar {
    private String nome;
    private String arma;
    private int forzaAttacco;

    public Avatar(String nome, String arma, int forzaAttacco) {
        this.nome = nome;
        this.arma = arma;
        this.forzaAttacco = forzaAttacco;
    }

    public void attacca(PostazioneRDA postazione) throws AttaccoFallitoException, ArmaMalfunzionanteException {
        if (Math.random() > 0.5) {
            if (Math.random() > 0.2) {
                try {
                    postazione.difendi(forzaAttacco);
                } catch (DifesaInsufficienteException | UnobtaniumEsauritoException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new ArmaMalfunzionanteException("L'arma di " + nome + " ha malfunzionato.");
            }
        } else {
            throw new AttaccoFallitoException("L'attacco di " + nome + " è fallito.");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getArma() {
        return arma;
    }

    public int getForzaAttacco() {
        return forzaAttacco;
    }
}