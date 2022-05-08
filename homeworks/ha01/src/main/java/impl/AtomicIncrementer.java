package impl;

import util.Incrementer;

public class AtomicIncrementer implements Incrementer {
    private Integer counter;
    private Integer originalValue;

    public AtomicIncrementer(int startValue) {
        this.counter = startValue;
        this.originalValue = startValue;
    }

    @Override
    public void increment() {
        synchronized (this) {
            this.counter += 1;
        }
    }

    @Override
    public void incrementBy(int amount) {
        synchronized (this) {
            this.counter += amount;
        }
    }

    @Override
    public int incrementAndGet() {
        synchronized (this) {
            this.counter += 1;
            return this.counter;
        }
    }

    @Override
    public int getandIncrement() {
        synchronized (this) {
            int aux = this.counter;
            this.counter += 1;
            return aux;
        }
    }

    @Override
    public int getValue() {
        synchronized (this) {
            return this.counter;
        }
    }

    @Override
    public int resetValue() {
        synchronized (this) {
            this.counter = this.originalValue;
            return this.counter;
        }
    }

    @Override
    public int setValue(int newValue) {
        synchronized (this) {
            this.counter = newValue;
            return this.counter;
        }
    }
}
