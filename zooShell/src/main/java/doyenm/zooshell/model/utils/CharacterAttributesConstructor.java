package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class CharacterAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public CharacterAttributes build(Specie specie) {
        double tmp = gaussianStatistics.nextDouble();
        return new CharacterAttributes(tmp);
    }
}
