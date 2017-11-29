package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.TerritoryAttributes;
import doyenm.zooshell.utils.GaussianStatistics;

/**
 *
 * @author doyenm
 */
public class TerritoryAttributesConstructor {

    private final GaussianStatistics gaussianStatistics = new GaussianStatistics();

    public TerritoryAttributes build(Specie specie) {
        int size = specie.getTerritoryAttributes().getTerritorySizeForOneGroup();
        int tmpSize = gaussianStatistics.gaussianInt(size, size / 10.0);
        return new TerritoryAttributes(tmpSize);
    }
}
