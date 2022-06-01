package impl;

import util.AbstractAthlete;
import util.AbstractWeight;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Weight extends AbstractWeight {
    private int holdingAthleteId = -1;
    public Semaphore weightSem = new Semaphore(1);

    /**
     * DO NOT CHANGE THE SIGNATURE OF THE CONSTRUCTOR!
     *
     * @param id The identifier of this weight. Must be unique among all weights.
     */
    public Weight(int id) {
        super(id);
    }

    /**
     * Determines whether the weight is available to be picked up by an athlete.
     * This is the case if the weight is not currently held by any athlete.
     *
     * @return false if the weight is held by an athlete, true otherwise.
     */
    @Override
    public boolean isAvailable() {
        synchronized (this) {
            return holdingAthleteId == -1;
        }
    }

    /**
     * Called by super.pickUp(). Implements the logic of checking whether the weight is available for pick-up
     * by an athlete and marking it as held by the athlete if it was available.
     *
     * @param athlete The athlete who is attempting to pick up the weight.
     * @return true if the weight was successfully picked up, false otherwise.
     */
    @Override
    protected boolean pickUpImpl(AbstractAthlete athlete) {
        int leftWeightId = athlete.getLeftWeight().getWeightId();
        int rightWeightId = athlete.getRightWeight().getWeightId();

        if (this.id == leftWeightId || this.id == rightWeightId) {
            if (isAvailable()) {
                holdingAthleteId = athlete.getAthleteId();
                return true;
            }
        }

        return false;
    }

    /**
     * Called by super.putDown(). Implements the logic of checking whether the weight may be put down by the
     * calling athlete (this is the case only if the weight is currently being held by this athlete) and releasing
     * the weight if applicable.
     *
     * @param athlete The athlete who is attempting to put down the weight.
     * @return true if the weight was successfully put down, false otherwise.
     */
    @Override
    protected boolean putDownImpl(AbstractAthlete athlete) {
        int leftWeightId = athlete.getLeftWeight().getWeightId();
        int rightWeightId = athlete.getRightWeight().getWeightId();

        if (this.holdingAthleteId == athlete.getAthleteId()) {
            if (this.id == leftWeightId || this.id == rightWeightId) {
                if (!isAvailable()) {
                    holdingAthleteId = -1;
                    return true;
                }
            }
        }

        return false;
    }
}
