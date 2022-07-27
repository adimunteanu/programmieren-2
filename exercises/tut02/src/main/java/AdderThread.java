public class AdderThread extends Thread {

    @Override
    public void run() {
        // 1. Geben Sie auf der Konsole die Identität des Threads (verwenden Sie hier this.getName())
        while (true) {
            System.out.println(this.getName() + "reads: " + RmwRaceCondition.counter);
            int count = RmwRaceCondition.counter;
            count += 1;
            RmwRaceCondition.counter = count;
        }
        // und den Wert den dieser durch die Referenz RmwRaceCondition.counter erhält aus
        // 2. Schreiben Sie diesen Wert in eine lokale Variable count und inkrementieren Sie den Wert dieser Variable
        // 3. Schreiben Sie den Wert der lokalen Variable count zurück in RmwRaceCondition.counter
        // 4. Wiederholen Sie abschließend die Ausgabe aus dem ersten Schritt

        // TODO: implement me
    }
}