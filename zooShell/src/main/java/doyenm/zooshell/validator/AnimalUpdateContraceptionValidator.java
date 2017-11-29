package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.validator.context.FindingContraceptionContext;
import doyenm.zooshell.validator.function.FindingContraceptionFunction;
import doyenm.zooshell.validator.predicates.CanHaveAChirurgicalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.CanHaveAHormonalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithPreviousPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithSexPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateContraceptionValidator
        implements Predicate<AnimalUpdateContraceptionContext> {

    private final FindingContraceptionFunction findingContraceptionMethodFunction;
    private final FindAnimal findAnimal;

    private final CanHaveAHormonalContraceptionPredicate hormonalContraceptionPredicate;
    private final CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate;
    private final IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate;
    private final IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate;

    @Override
    public boolean test(AnimalUpdateContraceptionContext t) {
        AnimalUpdateContraceptionContext context = retrieveAnimal(t);
        context = retrieveContraceptionMethod(context);
        return !Stream.of(context)
                .filter((AnimalUpdateContraceptionContext t1) -> {
                    return t1.getConvertedAnimal() != null && t1.getConvertedContraceptionMethod() != null;
                })
                .filter(compatibleWithSexPredicate)
                .filter(hormonalContraceptionPredicate)
                .filter(chirurgicalContraceptionPredicate)
                .filter(compatibleWithPreviousPredicate)
                .collect(Collectors.toList()).isEmpty();

    }

    private AnimalUpdateContraceptionContext retrieveAnimal(AnimalUpdateContraceptionContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
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
