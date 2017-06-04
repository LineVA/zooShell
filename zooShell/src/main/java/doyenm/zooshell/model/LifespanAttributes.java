package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class LifespanAttributes {

    private int femaleLifespan;
    private int maleLifespan;

    public LifespanAttributes() {
    }

    public LifespanAttributes(int femaleLifespan, int maleLifespan) {
        this.femaleLifespan = femaleLifespan;
        this.maleLifespan = maleLifespan;
    }

     public int getLifespanGivenSex(Sex sex) {
        return Sex.FEMALE == sex
                ? getFemaleLifespan(): getMaleLifespan();
    }
    
    @Override
    public String toString() {
        return "LifespanAttributes{" + "femaleLifespan=" + femaleLifespan + ", maleLifespan=" + maleLifespan + '}';
    }
    
}
