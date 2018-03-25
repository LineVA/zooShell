package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class IsContraceptionCompatibleWithPreviousPredicate
        implements Predicate<AnimalUpdateContraceptionContext> {

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        Animal animal = t.getConvertedAnimal();
        return !ContraceptionMethod.getPermanentMethods().contains(animal.getContraceptionMethod());
    }
}
