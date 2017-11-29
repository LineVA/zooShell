package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.SocialAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class SocialAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public SocialAttributes build(Specie specie) {
        int individuals = specie.getSocialAttributes().getIndividualsPerGroup();
        int tmpIndividuals = gaussianStatistics.gaussianInt(individuals, individuals / 10.0);
        return new SocialAttributes(tmpIndividuals);
    }
}
