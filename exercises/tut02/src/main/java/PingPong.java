/*
    Konzept: Time-partioning zur Orchestrierung von Threads, d.h. Threads arbeiten nur zu bestimmten Zeiten
    Gewünschte Ausgabe: immer "Ping" vor "Pong"
*/
public class PingPong implements Runnable {
    private final static int interval = 3000; // vordefiniertes Intervall
    private String message;

    public PingPong(String message) {
        this.message = message;
    }

    public void run() {
        while (true) {
            System.out.println(message);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
            // TODO: print "Ping" or "Pong"
        }
    }

    public static void main(String[] args) {
        // TODO: implement me

        Thread ping = new Thread(new PingPong("ping"));
        Thread pong = new Thread(new PingPong("pong"));

        ping.start();
        try {
            Thread.sleep(PingPong.interval / 2);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
        pong.start();
    }
}