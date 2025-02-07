class SecondoPiatto extends Piatto {
    public SecondoPiatto(String nome) {
        super(nome);
    }

    @Override
    public void prepara() {
        System.out.println("Preparazione del secondo piatto: " + nome);
    }
}
