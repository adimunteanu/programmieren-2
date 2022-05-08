package readandsort;

import impl.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Monitor;

import static org.junit.jupiter.api.Assertions.*;

public class TestReadAndSort {

    static int[] sorted = {3, 3, 3, 10, 11, 13, 14, 15, 17, 18, 18, 20, 25, 30, 38, 41, 42, 42, 44, 46, 47, 51, 54, 54, 55, 67, 67, 71, 72, 74, 75, 75, 76, 84, 84, 84, 88, 89, 90, 93};
    static int[] actualSorted = null;

    @BeforeEach
    void runReadAndSort() {
        Monitor.reset();
        actualSorted = Main.readAndSort("Numbers.txt");
    }

    @Test
    void testReadAndSort() {
        assertArrayEquals(sorted, actualSorted);
    }

    @Test
    void testThreadCount() {
        assertTrue(Monitor.getThreadCount() > 5);
    }

    @Test
    void testPartitionCount() {
        assertTrue(Monitor.getPartitionCount() > 5);
    }

    @Test
    void testNonexistentFile() {
        int[] fakeSorted = Main.readAndSort("FakeNumbers.txt");
        assertArrayEquals(new int[0], fakeSorted);
    }
}
