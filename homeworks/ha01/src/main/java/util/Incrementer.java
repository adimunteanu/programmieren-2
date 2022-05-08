package util;

/**
 * DO NOT MODIFY THIS FILE!
 */
public interface Incrementer {

    /**
     * Increments the internal counter by 1.
     */
    public void increment();

    /**
     * Increments the internal counter by a given amount.
     * @param amount amount to increment internal value by
     */
    public void incrementBy(int amount);

    /**
     * Increments the internal counter and returns the resulting value.
     * @return Internal value after incrementing.
     */
    public int incrementAndGet();

    /**
     * Increments the internal counter and returns its value as it was before.
     * @return Internal value before incrementing.
     */
    public int getandIncrement();

    /**
     * Returns the internal counter's value.
     * @return Internal counter's current value.
     */
    public int getValue();

    /**
     * Resets the internal counter to its original value.
     * @return New value of the internal counter.
     */
    public int resetValue();

    /**
     * Sets the internal counter to the given value.
     * @param newValue Value the internal counter should be set to.
     * @return New value of the internal counter.
     */
    public int setValue(int newValue);
}
