package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class SocialAttributes {

    private int individualsPerGroup;

    public SocialAttributes(int individualsPerGroup) {
        this.individualsPerGroup = individualsPerGroup;
    }

    public SocialAttributes() {
    }

    @Override
    public String toString() {
        return "SocialAttributes{" + "individualsPerGroup=" + individualsPerGroup + '}';
    }
    
}
