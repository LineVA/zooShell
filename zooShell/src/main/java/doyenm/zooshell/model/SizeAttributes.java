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

    private int femaleWeight;
    private int maleWeight;

    public SizeAttributes(int femaleWeight, int maleWeight) {
        this.femaleWeight = femaleWeight;
        this.maleWeight = maleWeight;
    }

    public SizeAttributes() {
    }

}
