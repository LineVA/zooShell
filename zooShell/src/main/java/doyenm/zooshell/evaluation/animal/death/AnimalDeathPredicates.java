package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.utils.UniformStatistics;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalDeathPredicates {

    private final int numberOfTurnsBeforeDying;
    private final double minProportionOfMaxWellBeingToProcrastinateDeath;
    private final double chanceToProcrastinateDeath;
    private final UniformStatistics uniformStatistics = new UniformStatistics();

    public boolean isDeadByOldAge(Animal animal, double maxWellBeingExceptKeepers) {
        int lifespan = animal.getLifespanAttributes().getLifespanGivenSex(animal.getSex());
        if (animal.getAge() < lifespan) {
            return false;
        } else {
            if(animal.getWellBeing() >= maxWellBeingExceptKeepers * minProportionOfMaxWellBeingToProcrastinateDeath){
                return uniformStatistics.uniform() < chanceToProcrastinateDeath;
            } 
            return true;
        }
    }

    public boolean isDeadByDrowning(Animal animal) {
        return animal.getDaysOfDrowning() >= numberOfTurnsBeforeDying;
    }

    public boolean isDeadByHunger(Animal animal) {
        return animal.getDaysOfHunger() >= numberOfTurnsBeforeDying;
    }

    public boolean isDeadByPredation(Animal animal) {
        return animal.getKiller() != null;
    }

}
