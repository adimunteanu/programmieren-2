package semaphore;

public class Kunde extends Thread{
    public final int kundenNr;
    private final Discounter discounter;

    public Kunde(int kundenNr, Discounter discounter) {
        this.kundenNr = kundenNr;
        this.discounter = discounter;
    }

    public void run() {
        while(!discounter.einkaufen(this)){
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}