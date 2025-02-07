public class Main {
    public static void main(String[] args) {
        try{
            Coordinata c = new Coordinata(180, 90);
            System.out.println(c);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
