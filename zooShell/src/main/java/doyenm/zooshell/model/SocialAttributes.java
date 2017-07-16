package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@ToString
public class SocialAttributes {
    private int individualsPerGroup;

    public SocialAttributes(int individualsPerGroup) {
        this.individualsPerGroup = individualsPerGroup;
    }

    public SocialAttributes() {
    }
}
