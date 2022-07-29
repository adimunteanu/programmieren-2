package functionalInterfaces;

import java.util.function.Predicate;

public class TestOdd implements Predicate<Integer> {
    @Override
    public boolean test(Integer x) {
        return x % 2 == 1;
    }
}
