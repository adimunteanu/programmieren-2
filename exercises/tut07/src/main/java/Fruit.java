import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fruit {
    String name;
    int weight;
    int calories;

    public Fruit(String name, int weight, int calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    @Override
    public String toString(){
        return this.name + " weight: " + this.weight + "g, calories: " + this.calories;
    }

    public static List<Fruit> fruits = new ArrayList<>(Arrays.asList(
       new Fruit("Apple", 200, 52),
       new Fruit("Orange", 400, 47),
       new Fruit("Kiwi", 150, 61),
       new Fruit("Pear", 100, 57),
       new Fruit("Banana", 500, 89),
       new Fruit("Mango", 250, 60),
       new Fruit("Dragon fruit", 400, 60),
       new Fruit("Watermelon", 600, 30)
    ));
}
