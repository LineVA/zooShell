package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithDietCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final LsWithCriteriaParser parser;
    private final FindingDietFunction findingDietFunction;

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")");

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        if (context.getDietsExpression() != null && !context.getDietsExpression().isEmpty() && context.getDiets() != null) {
            context.getDiets().addAll(parser.parse(context.getDietsExpression(), excluded));
            context.setDietsExpression(parser.replaceGrammaticalExpression(context.getDietsExpression()));
            context = retrieveDiet(context);
            if (context.getConvertedDiets() != null && context.getDiets() != null) {
                return context.getDiets().size() == context.getConvertedDiets().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrieveDiet(LsWithCriteriaContext t) {
        t.getDiets()
                .stream()
                .map((String t1) -> new FindingDietContext(t1))
                .map(findingDietFunction)
                .filter(e -> e.getConvertedDiet() != null)
                .forEach(e -> {
                    t.getConvertedDiets().put(e.getDiet(), e.getConvertedDiet());
                });
        return t;
    }
}
