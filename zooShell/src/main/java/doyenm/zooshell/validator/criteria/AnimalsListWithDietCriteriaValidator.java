package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.validator.criteria.LsWithCriteriaParser;
import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithDietCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final FindingDietFunction findingDietFunction;
    
    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        context = LsWithCriteriaParser.parse(context);
        context = retrieveDiet(context);
        if (context.getConvertedDiets()!= null) {
            return context.getDiets().size() == context.getConvertedDiets().size();
        }
        return false;
    }

    private LsWithCriteriaContext retrieveDiet(LsWithCriteriaContext t) {
        if (t.getDiets()!= null) {
                    t.getDiets()
                    .stream()
                    .map((String t1) -> new FindingDietContext(t1))
                    .map(findingDietFunction)
                    .filter(e -> e.getConvertedDiet() != null)
                    .forEach(e -> {
                        t.getConvertedDiets().put(e.getDiet(), e.getConvertedDiet());
                    });
        }
        return t;
    }
}
