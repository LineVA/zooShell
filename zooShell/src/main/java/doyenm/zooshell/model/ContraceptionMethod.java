package doyenm.zooshell.model;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public enum ContraceptionMethod {

    NONE(0),
    CASTRACTION(1),
    FEMALE_IMPLANT(2),
    FEMALE_PILL(3),
    MALE_IMPLANT(4),
    STERILIZATION(5);

    private final int id;

    ContraceptionMethod(int id) {
        this.id = id;
    }

    public static List<ContraceptionMethod> getMaleMethods() {
        return Arrays.asList(NONE, CASTRACTION, MALE_IMPLANT);
    }

    public static List<ContraceptionMethod> getFemaleMethods() {
        return Arrays.asList(NONE, STERILIZATION, FEMALE_IMPLANT, FEMALE_PILL);
    }

    public static List<ContraceptionMethod> getTemporaryMethods() {
        return Arrays.asList(FEMALE_IMPLANT, FEMALE_PILL, MALE_IMPLANT, NONE);
    }

    public static List<ContraceptionMethod> getHormonalMethods() {
        return Arrays.asList(FEMALE_IMPLANT, FEMALE_PILL, MALE_IMPLANT);
    }

    public static List<ContraceptionMethod> getPermanentMethods() {
        return Arrays.asList(STERILIZATION, CASTRACTION);
    }

    public static List<ContraceptionMethod> getChirurgicalMethods() {
        return Arrays.asList(STERILIZATION, CASTRACTION);
    }
}
