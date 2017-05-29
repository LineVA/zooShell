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

    @Override
    public String toString() {
        return "LifespanAttributes{" + "femaleLifespan=" + femaleLifespan + ", maleLifespan=" + maleLifespan + '}';
    }
    
}
