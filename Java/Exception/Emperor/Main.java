public class Main {
    public static void main(String[] args) {
        Città città = new Città(100);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Giorno " + i + ":");
            città.simulaGiorno();
            System.out.println();
        }
    }
}