package functionalInterfaces;

import java.util.function.UnaryOperator;

public class MapPlusThree implements UnaryOperator<Integer> {
    @Override
    public Integer apply(Integer x) {
        return x + 3;
    }
}
