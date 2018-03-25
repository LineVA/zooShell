package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalAgeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        context.getAnimal().setAge(t.getAnimal().getAge() + t.getZoo().getMonthsPerEvaluation());
        context.setAnimal(updateNecessityOfNursing(context.getAnimal()));
        return context;
    }
    
    private Animal updateNecessityOfNursing(Animal animal){
        if(animal.getAge() >= animal.getReproductionAttributes().getWeaningAge()){
            animal.setNotNursingByMother(false);
        }
        return animal;
    }

}
