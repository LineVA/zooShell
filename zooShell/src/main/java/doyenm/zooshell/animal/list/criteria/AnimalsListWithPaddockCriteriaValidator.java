package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.FindPaddock;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithPaddockCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final List<String> excluded;

    private final LsWithCriteriaParser parser;
    private final FindPaddock findPaddock;

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;

        if (context.getPaddocksExpression() != null
                && !context.getPaddocksExpression().isEmpty()
                && context.getPaddocks() != null) {
            context.getPaddocks().addAll(parser.parse(context.getPaddocksExpression(), excluded));
            context.setPaddocksExpression(parser.replaceGrammaticalExpression(context.getPaddocksExpression()));
            retrievePaddock(context);
            if (context.getConvertedPaddocks() != null) {
                return context.getPaddocks().size() == context.getConvertedPaddocks().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrievePaddock(LsWithCriteriaContext t) {
        t.getPaddocks()
                .stream()
                .map(pad -> findPaddock.find(t.getZoo(), pad))
                .filter(Objects::nonNull)
                .forEach(e ->
                    t.getConvertedPaddocks().put(e.getName(), e)
                );
        return t;
    }
}
