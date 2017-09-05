package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class WellBeing {
    private double biomeWellBeing = 0.0;
    private double dietsWellBeing = 0.0;
    private double foodQuantityWellBeing = 0.0;
    private double fastDaysWellBeing = 0.0;
    private double territoryWellBeing = 0.0;
    private double socialWellBeing = 0.0;
    private double keepersTaskWellBeing = 0.0;
    private double keepersTimeWellBeing = 0.0;
    
    public double computeWellBeing(){
        return (
                getBiomeWellBeing()
                + getDietsWellBeing()
                + getFoodQuantityWellBeing()
                + getFastDaysWellBeing()
                + getTerritoryWellBeing()
                + getSocialWellBeing()
                + getKeepersTaskWellBeing()
                + getKeepersTimeWellBeing()) 
                / 8.0;
    }
}
