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

}
