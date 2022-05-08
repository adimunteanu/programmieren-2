public class LoggerRunnable extends Logger implements Runnable{
    /* TODO: your code */
    String name;

    public LoggerRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            log(name, i);
        }
    }
}