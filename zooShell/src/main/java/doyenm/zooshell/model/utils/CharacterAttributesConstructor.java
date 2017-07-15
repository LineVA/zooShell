package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.SizeAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CharacterAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public CharacterAttributes build(Specie specie, SizeAttributes sizeAttributes, Sex sex) {
        double tmpAgressivity = gaussianStatistics.nextDouble();
        double tmpBravery = gaussianStatistics.nextDouble();
        double tmpGourmandise = gaussianStatistics.nextDouble();
        double tmpIntelligence = gaussianStatistics.nextDouble();
        double tmpCuriosity = gaussianStatistics.nextDouble();
        double tmpMeticulousness = gaussianStatistics.nextDouble();
        double cohabitationFactor = determineCohabitationFactor(tmpAgressivity, specie, sizeAttributes, sex);
        return new CharacterAttributes(tmpAgressivity, tmpBravery, cohabitationFactor,
                tmpCuriosity, tmpGourmandise, tmpIntelligence, tmpMeticulousness);
    }

    public double determineCohabitationFactor(double agressivity, Specie specie, SizeAttributes sizeAttributes, Sex sex) {
        double cohabitation = 0.0;
        double weight = sizeAttributes.getWigthGivenSex(sex);

        // Cohabitation = (
        //    weight/ 1 tonne
        //    + 1 if carnivore
        //    + agressivity
        //        + specie_agresivity)
        //    / 4
        return Stream.of(cohabitation)
                .map((Double t) -> {
                    t += weight >= 1000.0 ? 1.0 : weight / 1000.0;
                    return t;
                })
                .map((Double t) -> {
                    if (specie.getDiets().getDiets().contains(Diet.CARNIVOROUS)) {
                        t += 1.0;
                    }
                    return t;
                })
                .map((Double t) -> t += agressivity)
                .map((Double t) -> t += specie.getCharacterAttributes().getAgressivity())
                .map((Double t) -> t / 4.0)
                .findFirst()
                .get();
    }

}
