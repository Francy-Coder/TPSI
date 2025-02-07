class Dolce extends Piatto {
    public Dolce(String nome) {
        super(nome);
    }

    @Override
    public void prepara() {
        System.out.println("Preparazione del dolce: " + nome);
    }
}
