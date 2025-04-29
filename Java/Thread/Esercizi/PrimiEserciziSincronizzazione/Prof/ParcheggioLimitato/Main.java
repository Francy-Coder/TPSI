import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
    // Ci sono N macchine il parcheggio ha M posti
            // M < N
        ParkingLot parkingLot = new ParkingLot(5);
        ArrayList<Car> cars = new ArrayList<>();
        //Creo 10 thread
        for(int i = 0; i < 10; i++) cars.add(new Car("Car" + (i + 1), parkingLot));
        for (Car c : cars) c.start();
        for (Car c : cars) c.join();

        System.out.println("Parcheggio chiude :)");
    }
}
