package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class TerritoryAttributes {

    private int territorySizeForOneGroup;

    public TerritoryAttributes(int territorySizeForOneGroup) {
        this.territorySizeForOneGroup = territorySizeForOneGroup;
    }
    
     public TerritoryAttributes() {
    }

    @Override
    public String toString() {
        return "TerritoryAttributes{" + "territorySizeForOneGroup=" + territorySizeForOneGroup + '}';
    }
     
}
