package doyenm.zooshell.validator;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.criteria.AnimalsListWithDietCriteriaValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsWithCriteria implements Predicate<LsWithCriteriaContext>{
    
    private final AnimalsListWithDietCriteriaValidator animalsListWithDietCriteriaValidator; 

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        Optional optional = Arrays.asList(context)
                .stream()
                .filter(animalsListWithDietCriteriaValidator)
                .findFirst();
        return optional.isPresent();
    }

}
