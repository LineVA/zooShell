package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.evaluation.animal.KeeperUtils;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.PaddockType;

import java.util.Collection;
import java.util.List;

/**
 * @author doyenm
 */
public class AnimalDyingPredicates {

    KeeperUtils keeperUtils = new KeeperUtils();

    private static final int NUMBER_OF_DAYS_PER_WEEK = 7;

    public boolean isDyingByDrowning(Animal animal) {
        return PaddockType.AQUARIUM == animal.getPaddock().getType();
    }

    public boolean isDyingByFast(Animal animal) {
        return animal.getCurrentFoodAttributes().getFastDays() >= NUMBER_OF_DAYS_PER_WEEK;
    }

    public boolean isDyingByFoodQuantityToZero(Animal animal) {
        return animal.getCurrentFoodAttributes().getQuantity() <= 0.0;
    }

    public boolean isDyingByBadDiets(Animal animal) {
        List<Diet> specieDiets = animal.getSpecie().getDiets().getDiets();
        return !(specieDiets.isEmpty() || !animal.getDiets().stream().noneMatch(diet -> (specieDiets.contains(diet))));
    }

    public boolean isDyingByLackOfKeeper(Animal animal, Collection<AnimalKeeper> keepers) {
        return keepers
                .stream()
                .noneMatch(keeper -> (keeperUtils.isKeeperFeedingInGivenPaddock(keeper, animal.getPaddock())));
    }

    public boolean isDyingByLackOfNursing(Animal animal, Collection<AnimalKeeper> keepers) {
        if (animal.isNotNursingByMother()) {
            return keepers
                    .stream()
                    .noneMatch(keeper -> (keeperUtils.isKeeperNursingInGivenPaddock(keeper, animal.getPaddock())));
        }
        return false;
    }

}