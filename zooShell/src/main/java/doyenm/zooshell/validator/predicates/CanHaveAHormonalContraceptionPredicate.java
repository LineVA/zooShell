package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Sex;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class CanHaveAHormonalContraceptionPredicate implements Predicate<AnimalUpdateContraceptionContext> {

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        Animal animal = t.getConvertedAnimal();
        if (ContraceptionMethod.getHormonalMethods().contains(t.getConvertedContraceptionMethod())) {
            if (Sex.FEMALE == animal.getSex()) {
                return animal.getAge() >= animal.getReproductionAttributes().getFemaleMaturityAge();
            } else {
                return animal.getAge() >= animal.getReproductionAttributes().getMaleMaturityAge();
            }
        }
        return true;
    }

}
