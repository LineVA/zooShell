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
public class CanHaveAChirurgicalContraceptionPredicate implements Predicate<AnimalUpdateContraceptionContext> {

    private static final double THREE_QUARTER = 0.75;

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        Animal animal = t.getConvertedAnimal();
        if (ContraceptionMethod.getChirurgicalMethods().contains(t.getConvertedContraceptionMethod())) {
            if (Sex.FEMALE == animal.getSex()) {
                return animal.getAge() >= THREE_QUARTER * animal.getReproductionAttributes().getFemaleMaturityAge();
            } else {
                return animal.getAge() >= THREE_QUARTER * animal.getReproductionAttributes().getMaleMaturityAge();
            }
        }
        return true;
    }
}
