package functionalInterfaces;

import java.util.function.BinaryOperator;

public class Multiply implements BinaryOperator<Integer> {
    @Override
    public Integer apply(Integer x, Integer y) {
        return x * y;
    }
}
