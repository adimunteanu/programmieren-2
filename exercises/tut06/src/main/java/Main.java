import functionalInterfaces.MapPlusThree;
import functionalInterfaces.Multiply;
import functionalInterfaces.TestOdd;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        TestOdd tester = new TestOdd();
        Multiply mult = new Multiply();
        MapPlusThree mp3 = new MapPlusThree();

        System.out.println(tester.test(3));
        System.out.println(mult.apply(2,3));
        System.out.println(mp3.apply(1));

        //Als Lambda-Ausdruck
        Predicate<Integer> oddTester = i -> i % 2 == 1;
        System.out.println(oddTester.test(3));

        BinaryOperator<Integer> multiplier = (x, y) -> x * y;
        System.out.println(multiplier.apply(2,3));

        UnaryOperator<Integer> mapper = x -> x + 3;
        System.out.println(mapper.apply(1));
    }
}
