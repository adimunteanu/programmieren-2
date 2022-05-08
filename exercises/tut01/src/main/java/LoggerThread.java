public class LoggerThread extends Thread{
    String name;

    public LoggerThread(String name) {
        super.run();

        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(this.name + i);
        }
    }
}