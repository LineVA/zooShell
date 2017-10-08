package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDyingMeasures {

    private AnimalDyingPredicates dyingPredicates;

    public AnimalUpdateDyingMeasures() {
        this.dyingPredicates = new AnimalDyingPredicates();
    }

    public AnimalUpdateDyingMeasures(AnimalDyingPredicates predicates) {
        this.dyingPredicates = predicates;
    }

    public Animal updateIsDyingByDrowning(Animal animal) {
        if (dyingPredicates.isDyingByDrowning(animal)) {
            animal.setDaysOfDrowning(animal.getDaysOfDrowning() + 1);
        } else {
            animal.setDaysOfDrowning(0);
        }
        return animal;
    }

    public Animal updateIsDyingByHunger(Animal animal, Collection<AnimalKeeper> keepers) {
        if (dyingPredicates.isDyingByFast(animal)
                | dyingPredicates.isDyingByBadDiets(animal)
                | dyingPredicates.isDyingByFoodQuantityToZero(animal)
                | dyingPredicates.isDyingByLackOfKeeper(animal, keepers)
                | dyingPredicates.isDyingByLackOfNursing(animal, keepers)) {
            animal.setDaysOfHunger(animal.getDaysOfHunger() + 1);
        } else {
            animal.setDaysOfHunger(0);
        }
        return animal;
    }

    /*
     * To be killed by another animal : 
     * - must be in the same paddock
     * - must be of different species
     * - must have a diff of cohabitationFactor >= 0.4
     */
    public Animal updateIsDeadByPredation(Animal animal, List<Animal> otherAnimals) {
        double cohabitationFactor = animal.getCharacterAttributes().getCohabitationFactor();
        Optional optional = otherAnimals
                .stream()
                .filter(other ->  Math.abs(cohabitationFactor - other.getCharacterAttributes().getCohabitationFactor()) >= 0.4)
                .findFirst();
        if (optional.isPresent()) {
            animal.setKiller((Animal) optional.get());
        }
        return animal;
    }
}
