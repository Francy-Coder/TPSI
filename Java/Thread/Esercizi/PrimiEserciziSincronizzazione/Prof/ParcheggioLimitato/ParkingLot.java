public class ParkingLot {
    private int postiLiberi;

    public ParkingLot(int postiLiberi) {
        this.postiLiberi = postiLiberi;
    }

    public synchronized void enter(Car c) throws InterruptedException{
        if (postiLiberi <= 0) wait();
        System.out.println(c.getName() + " entra nel parcheggio (posti liberi: " + postiLiberi + ")");
        postiLiberi--;

    }
    public synchronized void exit(Car c){
        System.out.println(c.getName() + " esce dal parcheggio (posti liberi: " + postiLiberi + ")");
        postiLiberi++;
        notify();
    }
}
