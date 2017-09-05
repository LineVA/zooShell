package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class FoodAttributesConstructor {
 private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public FoodAttributes build(Specie specie) {
        double foodQuantity = specie.getFoodAttributes().getQuantity();
        double tmpQuantity = gaussianStatistics.gaussianDouble(foodQuantity, foodQuantity / 10.0);
        int fastDays = specie.getFoodAttributes().getFastDays();
        return new FoodAttributes(tmpQuantity, fastDays);

    }
}
