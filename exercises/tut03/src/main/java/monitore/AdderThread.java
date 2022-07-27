package monitore;

public class AdderThread extends Thread {
    @Override
    public void run() {
        RmwRaceCondition.incrementAndPrint(this.getName());
    }
}