package util;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DO NOT MODIFY THIS FILE!
 * This class provides insights into the runtime behaviour of its subclasses. Used by the automated grading suite.
 */
public abstract class Monitor {

    private static final AtomicInteger threadCount = new AtomicInteger();
    private static final LinkedList<Partition> partitions = new LinkedList<>();

    public Monitor(Partition p) {
        threadCount.getAndIncrement();
        synchronized (partitions) {
            partitions.add(p);
        }
    }

    public static int getThreadCount() {
        return threadCount.get();
    }

    public static int getPartitionCount() {
        synchronized (partitions) {
            return partitions.size();
        }
    }

    public static void reset() {
        threadCount.set(0);
        synchronized (partitions) {
            partitions.clear();
        }
    }
}
