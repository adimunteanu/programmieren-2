import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambdas {

    private static List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    private static List<Integer> empty = new ArrayList();

    public static void main(String[] args) {
        Function<Integer, Integer> f1 = (x) -> x + 1; // 1) + 2)
        Predicate<Integer> f2 = (x) -> x % 2 == 1; // 3)
        BinaryOperator<Integer> f3 = (x, y) -> x + y; // 4)

        numbers.stream().map(f1);
        numbers.stream().filter(f2);
        numbers.stream().reduce(f3);

    }
}
