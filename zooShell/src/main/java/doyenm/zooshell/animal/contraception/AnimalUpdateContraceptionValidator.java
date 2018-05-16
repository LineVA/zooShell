package doyenm.zooshell.animal.contraception;

import doyenm.zooshell.animal.FindingContraceptionContext;
import doyenm.zooshell.animal.FindingContraceptionFunction;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.common.FindAnimal;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
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
        retrieveContraceptionMethod(context);
        return !Stream.of(context)
                .filter(t1 -> t1.getConvertedAnimal() != null && t1.getConvertedContraceptionMethod() != null)
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
        ContraceptionMethod method = Stream.of(findingContraceptionMethodContext)
                .map(findingContraceptionMethodFunction)
                .findFirst()
                .orElse(
                        findingContraceptionMethodContext
                )
                .getConvertedContraception();

        t.setConvertedContraceptionMethod(method);

        return t;
    }
}
