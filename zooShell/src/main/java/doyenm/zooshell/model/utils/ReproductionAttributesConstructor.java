package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class ReproductionAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public ReproductionAttributes build(Specie specie) {
        int femaleMaturityAge = specie.getReproductionAttributes().getFemaleMaturityAge();
        int tmpFemaleMaturityAge = gaussianStatistics.gaussianInt(femaleMaturityAge, femaleMaturityAge / 10.0);
        int maleMaturityAge = specie.getReproductionAttributes().getMaleMaturityAge();
        int tmpMaleMaturityAge = gaussianStatistics.gaussianInt(maleMaturityAge, maleMaturityAge / 10.0);
        double frequency = specie.getReproductionAttributes().getFrequency();
        double tmpFrequency = gaussianStatistics.gaussianDouble(frequency, frequency / 10.0);
        int litterSize = specie.getReproductionAttributes().getLitterSize();
        int tmpLitterSize = gaussianStatistics.gaussianInt(litterSize, litterSize / 2.0);
        return new ReproductionAttributes(tmpFemaleMaturityAge, tmpMaleMaturityAge, tmpFrequency,
                tmpLitterSize);

    }
}
