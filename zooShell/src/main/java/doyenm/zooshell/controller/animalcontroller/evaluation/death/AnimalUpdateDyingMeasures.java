package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateDyingMeasures {

    private final AnimalDyingPredicates dyingPredicates;
    private final List<Double> cohabitationFactorsAccordingToHungerDays;

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
                || dyingPredicates.isDyingByBadDiets(animal)
                || dyingPredicates.isDyingByFoodQuantityToZero(animal)
                || dyingPredicates.isDyingByLackOfKeeper(animal, keepers)
                || dyingPredicates.isDyingByLackOfNursing(animal, keepers)) {
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
     * - the killer cannot be still nursing
     */
    public Animal updateIsDeadByPredation(Animal animal, List<Animal> otherAnimals) {
        double cohabitationFactor = animal.getCharacterAttributes().getCohabitationFactor();
        Optional optional = otherAnimals
                .stream()
                .filter(other -> other.getReproductionAttributes().getWeaningAge() < other.getAge() )
                .filter(other -> cohabitationFactor < other.getCharacterAttributes().getCohabitationFactor())
                .filter(other -> Math.abs(cohabitationFactor - other.getCharacterAttributes().getCohabitationFactor())
                        >= computeCohabitationFactorDelta(other))
                .findFirst();
        if (optional.isPresent()) {
            animal.setKiller((Animal) optional.get());
        }
        return animal;
    }
    
    private double computeCohabitationFactorDelta(Animal killer){
        return cohabitationFactorsAccordingToHungerDays.get(killer.getDaysOfHunger());
    }
}
