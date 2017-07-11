package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.controller.animalcontroller.evaluation.NursingKeeperPredicate;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.PaddockType;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicates {

    KeeperUtils feedingKeeperPredicate = new KeeperUtils();
    NursingKeeperPredicate nursingKeeperPredicate = new NursingKeeperPredicate();

    private final int NUMBER_OF_DAYS_PER_WEEK = 7;

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
        if (specieDiets.isEmpty()) {
            return false;
        }
        if (!animal.getDiets().stream().noneMatch((diet) -> (specieDiets.contains(diet)))) {
            return false;
        }
        return true;
    }

    public boolean isDyingByLackOfKeeper(Animal animal, Collection<AnimalKeeper> keepers) {
        if (!keepers.stream().noneMatch((keeper) -> (feedingKeeperPredicate.test(keeper, animal.getPaddock())))) {
            return false;
        }
        return true;
    }

    public boolean isDyingByLackOfNursing(Animal animal, Collection<AnimalKeeper> keepers) {
        if (animal.isNotNursingByMother()) {
            if (!keepers.stream().noneMatch((keeper) -> (nursingKeeperPredicate.test(keeper, animal.getPaddock())))) {
                return false;
            }
            return true;
        }
        return false;
    }

}
