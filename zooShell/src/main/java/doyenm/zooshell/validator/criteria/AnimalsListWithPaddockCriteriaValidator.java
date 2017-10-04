package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.FindPaddock;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithPaddockCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final LsWithCriteriaParser parser;
    private final FindPaddock findPaddock;

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")", ",");

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;

        if (context.getPaddocksExpression()!= null && !context.getPaddocksExpression().isEmpty() && context.getPaddocks()!= null) {
            context.getPaddocks().addAll(parser.parse(context.getPaddocksExpression(), excluded));
            context.setPaddocksExpression(parser.replaceGrammaticalExpression(context.getPaddocksExpression()));
            context = retrievePaddock(context);
            if (context.getConvertedPaddocks()!= null) {
                return context.getPaddocks().size() == context.getConvertedPaddocks().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrievePaddock(LsWithCriteriaContext t) {
        t.getPaddocks()
                .stream()
                .map(pad -> {
                    return findPaddock.find(t.getZoo(), pad);
                })
                .filter(e -> e != null)
                .forEach(e -> {
                    t.getConvertedPaddocks().put(e.getName(), e);
                });
        return t;
    }
}
