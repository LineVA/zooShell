package doyenm.zooshell.utils;

import java.util.Random;

/**
 *
 * @author doyenm
 */
public class UniformStatistics extends Random {

    public double uniform() {
        return super.nextDouble();
    }

    public int getRandomLowerOrEqualsThan(int max) {
        int tmpInt = super.nextInt(max);
        return tmpInt == 0 ? 1 : tmpInt;
    }
}
