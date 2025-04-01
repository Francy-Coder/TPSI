public class Bomba extends Thread{

    public Bomba() {
    }

    @Override
    public void run(){
        for(int i = 10; i > 0; i--){
            System.out.println("Countdown: " + i);
            try{
                sleep(1000);
            }catch (InterruptedException e){
                System.out.println(getName() + " interruzione");
                break;
            }
        }

        System.out.println("BOOM!");
    }
}
