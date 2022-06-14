package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class MonitoredList<E> extends ArrayList<E> {

    public MonitoredList() {
        super();
    }

    public MonitoredList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public Stream<E> stream() {
        return super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return super.parallelStream();
    }
}
