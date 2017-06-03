package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.SizeAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class SizeAttributesConstructor {
 private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public SizeAttributes build(Specie specie) {
        double female = specie.getSizeAttributes().getFemaleWeight();
        double tmpFemale = gaussianStatistics.gaussianDouble(female, female / 10.0);
        double male = specie.getSizeAttributes().getMaleWeight();
        double tmpMale = gaussianStatistics.gaussianDouble(male, male / 10.0);
        return new SizeAttributes(tmpFemale, tmpMale);
    }
}
