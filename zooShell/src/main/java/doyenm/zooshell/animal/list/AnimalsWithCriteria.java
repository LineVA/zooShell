package doyenm.zooshell.animal.list;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.animal.list.criteria.AnimalsListWithDietCriteriaValidator;
import doyenm.zooshell.animal.list.criteria.AnimalsListWithPaddockCriteriaValidator;
import doyenm.zooshell.animal.list.criteria.AnimalsListWithSexCriteriaValidator;
import doyenm.zooshell.animal.list.criteria.AnimalsListWithSpecieCriteriaValidator;
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
