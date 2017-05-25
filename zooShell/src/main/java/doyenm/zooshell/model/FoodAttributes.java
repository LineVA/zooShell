package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class FoodAttributes {

    private double quantity;
    private int fastDays;

    public FoodAttributes() {
    }

    public FoodAttributes(double quantity, int fastDays) {
        this.quantity = quantity;
        this.fastDays = fastDays;
    }
    
}
