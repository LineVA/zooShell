package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalDeathPredicates {
    
    private final int numberOfTurnsBeforeDying;
    
    public boolean isDeadByOldAge(Animal animal) {
        int lifespan = animal.getLifespanAttributes().getLifespanGivenSex(animal.getSex());
        return animal.getAge() >= lifespan;
    }
    
    public boolean isDeadByDrowning(Animal animal){
        return animal.getDaysOfDrowning() >= numberOfTurnsBeforeDying;
    }
    
    public boolean isDeadByHunger(Animal animal){
        return animal.getDaysOfHunger()>= numberOfTurnsBeforeDying;
    }
    
    public boolean isDeadByPredation(Animal animal){
        return animal.getKiller() != null;
    }
    
}
