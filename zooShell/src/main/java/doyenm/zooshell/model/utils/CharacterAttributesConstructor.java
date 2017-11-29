package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.SizeAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class CharacterAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();
    private final CohabitationFactorHandler cohabitationFactorHandler = new CohabitationFactorHandler();

    public CharacterAttributes build(Specie specie, SizeAttributes sizeAttributes, Sex sex) {
        double tmpAgressivity = gaussianStatistics.nextDouble();
        double tmpBravery = gaussianStatistics.nextDouble();
        double tmpGourmandise = gaussianStatistics.nextDouble();
        double tmpIntelligence = gaussianStatistics.nextDouble();
        double tmpCuriosity = gaussianStatistics.nextDouble();
        double tmpMeticulousness = gaussianStatistics.nextDouble();
        double cohabitationFactor = cohabitationFactorHandler.compute(tmpAgressivity, specie, sizeAttributes, sex);
        return new CharacterAttributes(tmpAgressivity, tmpBravery, cohabitationFactor,
                tmpCuriosity, tmpGourmandise, tmpIntelligence, tmpMeticulousness);
    }
}
