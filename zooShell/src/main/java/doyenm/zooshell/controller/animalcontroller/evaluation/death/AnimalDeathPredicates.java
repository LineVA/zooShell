package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicates {
    
    private final int NUMBER_FOR_DYING = 3;
    
    public boolean isDeadByOldAge(Animal animal) {
        int lifespan = animal.getLifespanAttributes().getLifespanGivenSex(animal.getSex());
        return animal.getAge() >= lifespan;
    }
    
    public boolean isDeadByDrowning(Animal animal){
        return animal.getDaysOfDrowning() >= NUMBER_FOR_DYING;
    }
    
    public boolean isDeadByHunger(Animal animal){
        return animal.getDaysOfHunger()>= NUMBER_FOR_DYING;
    }
    
}
