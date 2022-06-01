package impl;

import util.AbstractAthlete;
import util.AbstractWeight;

import java.util.concurrent.Semaphore;

/**
 * DO NOT CHANGE THE SIGNATURE OF THIS CLASS!
 * <p>
 * Represents an athlete in our gym. Extends AbstractAthlete, which in turn extends Thread.
 * <p>
 * An athlete performs a number of training cycles. In each cycle, the following sequence is performed:
 * <p>
 * - the athlete stretches,
 * - picks up both weights
 * - exercises,
 * - and puts down both weights.
 */
public class Athlete extends AbstractAthlete {

    /**
     * DO NOT CHANGE THE SIGNATURE OF THE CONSTRUCTOR!
     *
     * @param id          The identifier of this athlete. Must be unique among all athletes.
     * @param cycles      Specifies how many times the athlete should perform the training sequence.
     * @param leftWeight  The weight to the left of the athlete.
     * @param rightWeight The weight to the right of the athlete.
     */
    public Athlete(int id, int cycles, AbstractWeight leftWeight, AbstractWeight rightWeight) {
        super(id, cycles, leftWeight, rightWeight);
    }

    /**
     * Implements the athlete's training cycle. The following sequence is performed @cycle times:
     * 1. stretch - call super.stretch()
     * 2. pick up both weights - call pickUp() on both
     * 3. exercise - call super.exercise()
     * 4. put down both weights - call putDown() on both
     * <p>
     * Ensure the following:
     * - no starvation: every athlete gets perform their full training cycle
     * - no deadlocks: the program always completes successfully
     * - no concurrent access: a weight is only ever held by up to one athlete at any given time
     * <p>
     * For maximum points:
     * - on step 2 of the sequence, the athlete calls pickUp() exactly once on each weight
     * - on step 4 of the sequence, the athlete calls putDown() exactly once on each weight
     */
    @Override
    public void run() {
        for (int i = 0; i < cycles; i++) {
            if (leftWeight.isAvailable()) {
                try {
                    ((Weight) leftWeight).weightSem.acquire();
                    try {
                        ((Weight) rightWeight).weightSem.acquire();
                        try {
                            stretch();
                            leftWeight.pickUp(this);
                            rightWeight.pickUp(this);
                            exercise();
                            leftWeight.putDown(this);
                            rightWeight.putDown(this);
                        } finally {
                            ((Weight) rightWeight).weightSem.release();
                        }
                    } finally {
                        ((Weight) leftWeight).weightSem.release();
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
