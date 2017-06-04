package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class ReproductionAttributes {

    private int femaleMaturityAge;
    private int maleMaturityAge;
    private double frequency;
    private int litterSize;
    private int gestationDuration;

    public ReproductionAttributes(int femaleMaturityAge, int maleMaturityAge, double frequency,
            int litter, int duration) {
        this.femaleMaturityAge = femaleMaturityAge;
        this.maleMaturityAge = maleMaturityAge;
        this.frequency = frequency;
        this.litterSize = litter;
        this.gestationDuration = duration;
    }

    public ReproductionAttributes() {
    }

    public int getMaturityGivenSex(Sex sex) {
        return Sex.FEMALE == sex
                ? getFemaleMaturityAge() : getMaleMaturityAge();
    }

    @Override
    public String toString() {
        return "ReproductionAttributes{" + "femaleMaturityAge=" + femaleMaturityAge +
                ", maleMaturityAge=" + maleMaturityAge +
                ", frequency=" + frequency + 
                ", litterSize=" + litterSize + 
                ", gestationDuration=" + gestationDuration + '}';
    }

}
