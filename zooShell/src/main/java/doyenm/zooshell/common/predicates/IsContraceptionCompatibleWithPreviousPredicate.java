package doyenm.zooshell.common.predicates;

import doyenm.zooshell.animal.contraception.AnimalUpdateContraceptionContext;
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
