package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.context.FindingContraceptionContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import doyenm.zooshell.validator.function.FindingContraceptionFunction;
import doyenm.zooshell.validator.predicates.CanHaveAChirurgicalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.CanHaveAHormonalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithPreviousPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithSexPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionValidator
        implements Predicate<AnimalUpdateContraceptionContext> {

    FindingContraceptionFunction findingContraceptionMethodFunction = new FindingContraceptionFunction();
    FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();

    CanHaveAHormonalContraceptionPredicate hormonalContraceptionPredicate
            = new CanHaveAHormonalContraceptionPredicate();
    CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate
            = new CanHaveAChirurgicalContraceptionPredicate();
    IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate
            = new IsContraceptionCompatibleWithPreviousPredicate();
    IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate
            = new IsContraceptionCompatibleWithSexPredicate();

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        AnimalUpdateContraceptionContext context = retrieveAnimal(t);
        context = retrieveContraceptionMethod(context);
        return !Stream.of(context)
                .filter((AnimalUpdateContraceptionContext t1) -> {
                    if (t1.getConvertedAnimal() != null && t1.getConvertedContraceptionMethod() != null) {
                        return t1.getConvertedAnimal().getPaddock().getEntry() != null;
                    }
                    return false;
                })
                .filter(compatibleWithSexPredicate)
                .filter(hormonalContraceptionPredicate)
                .filter(chirurgicalContraceptionPredicate)
                .filter(compatibleWithPreviousPredicate)
                .collect(Collectors.toList()).isEmpty();

    }

    private AnimalUpdateContraceptionContext retrieveAnimal(AnimalUpdateContraceptionContext t) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal());
        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
        return t;
    }

    private AnimalUpdateContraceptionContext retrieveContraceptionMethod(AnimalUpdateContraceptionContext t) {
        FindingContraceptionContext findingContraceptionMethodContext
                = new FindingContraceptionContext(t.getContraceptionMethod());
        t.setConvertedContraceptionMethod(Stream.of(findingContraceptionMethodContext)
                .map(findingContraceptionMethodFunction)
                .findFirst()
                .get()
                .getConvertedContraception());
        return t;
    }
}
