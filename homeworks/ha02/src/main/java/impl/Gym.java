package impl;

public class Gym {

    /**
     * DO NOT CHANGE THE SIGNATURE OF THIS METHOD!
     * <p>
     * Sets up the gym with the specified number of athletes and prepares them for specified number of training cycles.
     * If the specified number of athletes is smaller than 1, one athlete and two weights are created.
     * Otherwise, the number of weights is equal to the number of athletes. A weight is always shared by two athletes:
     * for the first athlete it's the left-hand weight, for the second athlete it's the right-hand weight.
     *
     * @param numAthletes Number of athletes to be created.
     * @param cycles      Number of training cycles, i.e. how many times each athlete should perform the training sequence.
     * @return Array of prepared athletes.
     */
    public static Athlete[] setup(int numAthletes, int cycles) {
        int athletesAmount = numAthletes <= 0 ? 1 : numAthletes;
        int cyclesAmount = cycles <= 0 ? 1 : cycles;

        Athlete[] athletes = new Athlete[athletesAmount];

        Weight leftWeight = new Weight(0);
        Weight rightWeight = new Weight(1);
        athletes[0] = new Athlete(0, cyclesAmount, leftWeight, rightWeight);
        Weight firstWeight = leftWeight;
        leftWeight = rightWeight;

        if (athletesAmount == 1) {
            return athletes;
        }

        for (int i = 1; i < athletesAmount - 1; i++) {
            Weight newWeight = new Weight(i + 1);
            athletes[i] = new Athlete(i, cyclesAmount, leftWeight, newWeight);
            leftWeight = newWeight;
        }

        athletes[athletes.length - 1] = new Athlete(athletes.length - 1, cyclesAmount, leftWeight, firstWeight);

        return athletes;
    }
}
