package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicates {
    
    private final int NUMBER_FOR_DYING = 3;
    
    public boolean isDeadByAge(Animal animal) {
        int lifespan = Sex.FEMALE == animal.getSex()
                ? animal.getLifespanAttributes().getFemaleLifespan() : animal.getLifespanAttributes().getMaleLifespan();
        return animal.getAge() >= lifespan;
    }
    
    public boolean isDeadByDrowning(Animal animal){
        return animal.getDaysOfDrowning() >= NUMBER_FOR_DYING;
    }
    
    public boolean isDeadByFast(Animal animal){
        return animal.getDaysOfFast()>= NUMBER_FOR_DYING;
    }
    
}
