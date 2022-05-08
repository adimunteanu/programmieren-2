package incrementer;

import impl.AtomicIncrementer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAtomicIncrementer {

    private final int defaultThreadPoolSize = 8;
    private final int defaultThreadCount = 24;

    @Test
    void testIncrement() {
        AtomicIncrementer inc = new AtomicIncrementer(0);
        executeOperation(() -> {
            for(int j = 0; j < 1000; j++) {
                inc.increment();
            }
            return null;
        }, inc, defaultThreadCount, defaultThreadPoolSize);

        assertEquals(defaultThreadCount * 1000, inc.getValue());
    }

    @Test
    void testIncrementBy() {
        AtomicIncrementer inc = new AtomicIncrementer(0);
        executeOperation(() -> {
            for(int j = 0; j < 1000; j++) {
                inc.incrementBy(2);
            }
            return null;
        }, inc, defaultThreadCount, defaultThreadPoolSize);

        assertEquals(defaultThreadCount * 1000 * 2, inc.getValue());
    }

    @Test
    void testIncrementAndGet() {
        AtomicIncrementer inc = new AtomicIncrementer(0);
        List<Future<Integer>> futures = executeOperation(inc::incrementAndGet, inc, 500, defaultThreadPoolSize);
        int maxValue = 0;
        for(Future<Integer> f : futures) {
            try {
                int value = f.get();
                if(maxValue < value) {
                    maxValue = value;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        assertEquals(500, maxValue);
    }

    @Test
    void testGetAndIncrement() {
        AtomicIncrementer inc = new AtomicIncrementer(0);
        List<Future<Integer>> futures = executeOperation(inc::getandIncrement, inc, 500, defaultThreadPoolSize);
        int maxValue = 0;
        for(Future<Integer> f : futures) {
            try {
                int value = f.get();
                if(maxValue < value) {
                    maxValue = value;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        assertEquals(499, maxValue);
    }

    @Test
    void testResetValue() {
        int startValue = (int) (Math.random() * 500);
        AtomicIncrementer inc = new AtomicIncrementer(startValue);
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadPoolSize);
        for(int i = 0; i < defaultThreadCount; i++) {
            executor.submit(() -> {
                for(int j = 0; j < 1000; j++) {
                    inc.increment();
                }
                return null;
            });
        }
        try {
            assertEquals(startValue, executor.submit(inc::resetValue).get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
            executor.shutdown();
            assertTrue(executor.awaitTermination(60, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        inc.resetValue();
        assertEquals(startValue, inc.getValue());
    }

    @Test
    void testSetValue() {
        int newValue = (int) (Math.random() * 100);
        AtomicIncrementer inc = new AtomicIncrementer(0);
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadPoolSize);
        for(int i = 0; i < defaultThreadCount; i++) {
            executor.submit(() -> {
                for(int j = 0; j < 1000; j++) {
                    inc.increment();
                }
                return null;
            });
        }
        try {
            assertEquals(newValue, executor.submit(() -> inc.setValue(newValue)).get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
            executor.shutdown();
            assertTrue(executor.awaitTermination(60, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        inc.setValue(newValue);
        assertEquals(newValue, inc.getValue());
    }

    private List<Future<Integer>> executeOperation(Callable<Integer> op, AtomicIncrementer inc, int threadCount, int threadPoolSize) {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        List<Callable<Integer>> ops = new ArrayList<>(threadCount);
        for(int i = 0; i < threadCount; i++) {
            ops.add(op);
        }
        try {
            return executor.invokeAll(ops);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
