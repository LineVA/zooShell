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

    public ReproductionAttributes(int femaleMaturityAge, int maleMaturityAge, double frequency, int litter) {
        this.femaleMaturityAge = femaleMaturityAge;
        this.maleMaturityAge = maleMaturityAge;
        this.frequency = frequency;
        this.litterSize = litter;
    }

    public ReproductionAttributes() {
    }
    
    public int getMaturityGivenSex(Sex sex){
        return Sex.FEMALE == sex ?
                getFemaleMaturityAge() : getMaleMaturityAge();
    }
    
    
}
