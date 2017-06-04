package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class SizeAttributes {

    private double femaleWeight;
    private double maleWeight;

    public SizeAttributes(double femaleWeight, double maleWeight) {
        this.femaleWeight = femaleWeight;
        this.maleWeight = maleWeight;
    }

    public SizeAttributes() {
    }
    
       public double getWigthGivenSex(Sex sex){
        return Sex.FEMALE == sex ?
                getFemaleWeight(): getMaleWeight();
    }

    @Override
    public String toString() {
        return "SizeAttributes{" + "femaleWeight=" + femaleWeight + ", maleWeight=" + maleWeight + '}';
    }

}
