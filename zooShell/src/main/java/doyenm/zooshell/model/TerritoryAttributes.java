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
public class TerritoryAttributes {

    private int territorySizeForOneGroup;

    public TerritoryAttributes(int territorySizeForOneGroup) {
        this.territorySizeForOneGroup = territorySizeForOneGroup;
    }
    
     public TerritoryAttributes() {
    }
}
