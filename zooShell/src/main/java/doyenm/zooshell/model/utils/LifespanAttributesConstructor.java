package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.LifespanAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class LifespanAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public LifespanAttributes build(Specie specie) {
        int femaleLifespan = specie.getLifespanAttributes().getFemaleLifespan();
        int tmpFemaleLifespan = gaussianStatistics.gaussianInt(femaleLifespan, femaleLifespan / 10.0);
        int maleLifespan = specie.getLifespanAttributes().getMaleLifespan();
        int tmpMaleLifespan = gaussianStatistics.gaussianInt(maleLifespan, maleLifespan / 10.0);
        return new LifespanAttributes(tmpFemaleLifespan, tmpMaleLifespan);

    }
}
