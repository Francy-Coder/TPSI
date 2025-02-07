public class Main {
    public static void main(String[] args) {
        Sugo bolognese = new Sugo("Bolognese");
        Sugo siciliana = new Sugo("Siciliana");

        Pasta pasta1 = new Pasta("Spaghetti", bolognese);
        Pasta pasta2 = new Pasta("Penne", siciliana);

        SecondoPiatto frittata = new SecondoPiatto("Frittata");
        Contorno verdureGrigliate = new Contorno("Verdure Grigliate");
        Dolce tortaDiPinoli = new Dolce("Torta di Pinoli");

        Cuoco cuoco = new Cuoco();

        cuoco.prepara(pasta1);
        cuoco.prepara(pasta2);
        cuoco.prepara(frittata);
        cuoco.prepara(verdureGrigliate);
        cuoco.prepara(tortaDiPinoli);
    }
}
