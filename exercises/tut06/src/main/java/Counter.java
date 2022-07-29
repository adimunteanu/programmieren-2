import java.util.Arrays;

public class Counter {
    private int counter = 0;

    public Counter(int x) {
        this.counter = x;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int i) {
        this.counter = i;
    }

    public boolean isEven() {
        return this.counter % 2 == 0;
    }

    public Counter incrementCounter() {
        this.counter += 1;
        return this;
    }

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(Counter::new)
                .filter(Counter::isEven)
                .map(Counter::incrementCounter)
                .map(Counter::getCounter)
                .forEach(System.out::println);
    }
}