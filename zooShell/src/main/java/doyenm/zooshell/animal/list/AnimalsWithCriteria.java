package doyenm.zooshell.animal.list;

import doyenm.zooshell.animal.LsWithCriteriaContext;
import doyenm.zooshell.animal.list.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

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
    private final AnimalsListWithContraceptionCriteriaValidator animalsListWithContraceptionCriteriaValidator;

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        Optional optional = Arrays.asList(context)
                .stream()
                .filter(animalsListWithDietCriteriaValidator)
                .filter(animalsListWithSexCriteriaValidator)
                .filter(animalsListWithPaddockCriteriaValidator)
                .filter(animalsListWithSpecieCriteriaValidator)
                .filter(animalsListWithContraceptionCriteriaValidator)
                .findFirst();
        return optional.isPresent();
    }

}
