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
public class IsContraceptionCompatibleWithSexPredicate implements Predicate<AnimalUpdateContraceptionContext> {

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        Animal animal = t.getConvertedAnimal();
        return Sex.FEMALE == animal.getSex()
                ? ContraceptionMethod.getFemaleMethods().contains(t.getConvertedContraceptionMethod())
                : ContraceptionMethod.getMaleMethods().contains(t.getConvertedContraceptionMethod());
    }
}
