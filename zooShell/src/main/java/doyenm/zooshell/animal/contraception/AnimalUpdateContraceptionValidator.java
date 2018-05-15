package doyenm.zooshell.animal.contraception;

import doyenm.zooshell.common.FindAnimal;
import doyenm.zooshell.common.context.FindingContraceptionContext;
import doyenm.zooshell.common.function.FindingContraceptionFunction;
import doyenm.zooshell.common.predicates.CanHaveAChirurgicalContraceptionPredicate;
import doyenm.zooshell.common.predicates.CanHaveAHormonalContraceptionPredicate;
import doyenm.zooshell.common.predicates.IsContraceptionCompatibleWithPreviousPredicate;
import doyenm.zooshell.common.predicates.IsContraceptionCompatibleWithSexPredicate;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.ContraceptionMethod;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateContraceptionValidator
        implements Function<AnimalUpdateContraceptionContext, AnimalUpdateContraceptionContext> {

    private final FindingContraceptionFunction findingContraceptionMethodFunction;
    private final FindAnimal findAnimal;

    private final CanHaveAHormonalContraceptionPredicate hormonalContraceptionPredicate;
    private final CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate;
    private final IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate;
    private final IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate;

    @Override
    public AnimalUpdateContraceptionContext apply(AnimalUpdateContraceptionContext t) {
        AnimalUpdateContraceptionContext context = retrieveAnimal(t);
        retrieveContraceptionMethod(context);
        if(context.getErrors().isEmpty()) {
            boolean isAdequate = Stream.of(context)
                    .filter(compatibleWithSexPredicate)
                    .filter(hormonalContraceptionPredicate)
                    .filter(chirurgicalContraceptionPredicate)
                    .filter(compatibleWithPreviousPredicate)
                    .anyMatch(i -> true);
            if(!isAdequate){
                t.getErrors().add(new BusinessError(ErrorType.INADEQUATE_CONTRACEPTION));
            }
        }
         return context;

    }

    private AnimalUpdateContraceptionContext retrieveAnimal(AnimalUpdateContraceptionContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
        if(t.getConvertedAnimal() == null){
            t.getErrors().add(new BusinessError(ErrorType.UNKNOWN_ANIMAL));
        }
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
        if(method == null){
            t.getErrors().add(new BusinessError(ErrorType.UNKNOWN_CONTRACEPTION));
        }
        t.setConvertedContraceptionMethod(method);

        return t;
    }
}
