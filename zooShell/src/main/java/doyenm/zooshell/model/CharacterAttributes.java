package doyenm.zooshell.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class CharacterAttributes {

    private double agressivity;
    private double cohabitationFactor;

    public CharacterAttributes(double agressivity, double cohabitationFactor) {
        this.agressivity = agressivity;
        this.cohabitationFactor = cohabitationFactor;
    }
    
     public CharacterAttributes() {
    }

    @Override
    public String toString() {
        return "CharacterAttributes{" + "agressivity=" + agressivity + '}';
    }
    
    
}
