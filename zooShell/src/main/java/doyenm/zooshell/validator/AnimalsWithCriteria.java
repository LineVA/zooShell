package doyenm.zooshell.validator;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.criteria.AnimalsListWithDietCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithPaddockCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSexCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSpecieCriteriaValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsWithCriteria implements Predicate<LsWithCriteriaContext> {

    private final AnimalsListWithDietCriteriaValidator animalsListWithDietCriteriaValidator;
    private final AnimalsListWithSexCriteriaValidator animalsListWithSexCriteriaValidator;
    private final AnimalsListWithPaddockCriteriaValidator animalsListWithPaddockCriteriaValidator;
    private final AnimalsListWithSpecieCriteriaValidator animalsListWithSpecieCriteriaValidator;

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        Optional optional = Arrays.asList(context)
                .stream()
                .filter(animalsListWithDietCriteriaValidator)
                .filter(animalsListWithSexCriteriaValidator)
                .filter(animalsListWithPaddockCriteriaValidator)
                .filter(animalsListWithSpecieCriteriaValidator)
                .findFirst();
        return optional.isPresent();
    }

}
