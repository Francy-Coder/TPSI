import java.util.Random;

public class Car extends Thread{
    private ParkingLot parkingLot;
    private final int MAX_DRIVE_TIME = 1500;
    private final int MAX_PARK_TIME = 1000;

    private Random r;

    public Car(String name, ParkingLot parkingLot) {
        super(name);
        this.parkingLot = parkingLot;
        this.r = new Random();
    }

    @Override
    public void run() {
        /*
        1) Guida per un po'
        2) Cerca di entrare
        3) Ci sta per un po'
        4) Esce
         */
        try {
            //1)
            Thread.sleep(r.nextInt(500, MAX_DRIVE_TIME));
            //2)
            parkingLot.enter(this);
            //3)
            Thread.sleep(r.nextInt(500, MAX_PARK_TIME));
            //4)
            parkingLot.exit(this);
        }catch (InterruptedException interruptedException){
            System.out.println(getName() + " interrotto");
        }

    }
}

