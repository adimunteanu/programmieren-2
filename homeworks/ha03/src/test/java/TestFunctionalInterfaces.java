import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static impl.FunctionalInterfaces.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunctionalInterfaces {

    @Test
    public void testApplyMathToOneInt() {
        int modifiedNumber = applyMathToOneInt(15, i -> i + 3);
        assertEquals(18, modifiedNumber);
    }

    @Test
    public void testApplyMathToTwoInts() {
        int resultOfMath = applyMathToTwoInts(10, 2, (int a, int b) -> (a % 3) + (b * 5));
        assertEquals(11, resultOfMath);
    }

    @Test
    public void testConcatenateIntst() {
        String concatenatedInts = concatenateInts(5, 3, (a, b) -> "One: " + a + ", Two: " + b);
        assertEquals("One: 5, Two: 3", concatenatedInts);
    }

    @Test
    public void testFilterIntegers() {
        List<Integer> nums = Arrays.asList(2, 3, 10, 4, 1, 6, 7, 8, 5, 9);
        Integer[] expected = {1, 2, 3, 4, 5};
        List<Integer> actual = filterIntegers(nums, i -> i <= 5);

        assertArrayEquals(expected, actual.stream().sorted().toArray());
    }






}
