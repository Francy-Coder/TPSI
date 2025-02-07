class Pasta extends Piatto {
    private Sugo sugo;

    public Pasta(String nome, Sugo sugo) {
        super(nome);
        this.sugo = sugo;
    }

    @Override
    public void prepara() {
        System.out.println("Preparazione della pasta con il sugo " + sugo.getTipo());
    }
}
