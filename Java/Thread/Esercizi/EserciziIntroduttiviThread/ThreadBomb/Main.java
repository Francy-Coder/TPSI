import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Bomba b = new Bomba();
        b.start();

        s.nextLine();
        b.interrupt();
    }
}
