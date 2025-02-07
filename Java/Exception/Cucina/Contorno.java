class Contorno extends Piatto {
    public Contorno(String nome) {
        super(nome);
    }

    @Override
    public void prepara() {
        System.out.println("Preparazione del contorno: " + nome);
    }
}
