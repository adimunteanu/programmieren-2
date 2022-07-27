package semaphore;

import java.util.concurrent.Semaphore;

public class Discounter {
    static int kundenZahl = 20;
    static Discounter aldi = new Discounter();
    static int counter = 0;
    Semaphore semaphore = new Semaphore(4);

    public boolean einkaufen(Kunde kunde) {
        try {
            semaphore.acquire();
            System.out.println("discounter.Kunde " + kunde.kundenNr + " darf einkaufen");
            Thread.sleep(1000 + (int) (Math.random() * 4000));
            System.out.println("discounter.Kunde " + kunde.kundenNr + " geht");

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < kundenZahl; i++) {
            new Kunde(i, aldi).start();
        }
    }
}
