import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Fruit> fruitBowl = Fruit.fruits;

    public static void main(String[] args) {
        System.out.println(
                fruitBowl.stream()
                        .map(fruit -> fruit.weight / 100 * fruit.calories)
                        .reduce(0, (a, b) -> a + b)
        );

        System.out.println(
                fruitBowl.stream()
                        .filter(fruit -> fruit.weight / 100 * fruit.calories > 200)
                        .collect(Collectors.toList())
        );

        System.out.println(
                fruitBowl.stream()
                        .min(Comparator.comparingInt(fruit -> fruit.calories)).get().name
        );

        System.out.println(
                fruitBowl.stream()
                        .map(fruit -> fruit.name.replaceAll("\\s+","").length())
                        .reduce(0, Integer::sum)
        );
    }
}
