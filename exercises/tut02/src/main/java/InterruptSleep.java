/*
    Konzept: Sleep & Wake, Threads vollrichten ihre Arbeite wecken den nächsten und legen sich schlafen, bis sie wieder geweckt werden
    Gewünschte Ausgabe: immer "Ping" vor "Pong"
*/
public class InterruptSleep extends Thread {
    private final static int interval = 3000;
    public InterruptSleep otherThread;
    private String message;

    public InterruptSleep(String message) {
        this.message = message;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(this.message);
                Thread.sleep(InterruptSleep.interval);
                if (otherThread.isAlive()) {
                    otherThread.interrupt();
                } else {
                    otherThread.start();
                }

                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
//                throw new RuntimeException();
            }
            // TODO: print "Ping" or "Pong"
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO: implement me
        InterruptSleep ping = new InterruptSleep("ping");
        InterruptSleep pong = new InterruptSleep("pong");

        ping.otherThread = pong;
        pong.otherThread = ping;

        ping.start();
    }
}