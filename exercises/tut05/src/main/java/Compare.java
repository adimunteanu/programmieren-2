import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class Compare {
    private static List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    private static List<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 0, 3, 4, 1, 1, 2, 8, 8, 1));

    /* TODO implement compare() */
    public static List<Boolean> compare(List<Integer> list1, List<Integer> list2, BiPredicate<Integer, Integer> predicate) {
        List<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(predicate.test(list1.get(i), list2.get(i)));
        }

        return result;
    }

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> bp = (x, y) -> x == y;
        BiFunction<Integer, Integer, Boolean> bf = (x, y) -> x == y;


        List<Boolean> res1 = compare(numbers1, numbers2, bp);
        System.out.println(res1);
//        List<Boolean> res2 = compare(numbers1, numbers2, bf);
        List<Boolean> res3 = compare(numbers1, numbers2, (x, y) -> x == y);
        System.out.println(res3);
    }
}
