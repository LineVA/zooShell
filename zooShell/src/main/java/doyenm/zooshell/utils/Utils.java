package doyenm.zooshell.utils;

/**
 * @author doyenm
 */
public class Utils {

    private static final int NUMBER_OF_MONTHS_PER_YEAR = 12;

    public int getNumbersOfYearsFromAge(int age) {
        return age / this.NUMBER_OF_MONTHS_PER_YEAR;
    }

    public int getNumbersOfMonthsFromAge(int age) {
        return age % this.NUMBER_OF_MONTHS_PER_YEAR;
    }
}
