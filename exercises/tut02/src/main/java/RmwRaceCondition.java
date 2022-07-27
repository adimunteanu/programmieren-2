public class RmwRaceCondition {
    public static int counter = 0;

    public static void main(String[] args) {
        for (int i=0; i < 25; i++) {
            new AdderThread().start();
        }
    }
}