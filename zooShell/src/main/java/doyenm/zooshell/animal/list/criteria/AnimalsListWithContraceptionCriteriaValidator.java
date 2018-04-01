package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.context.FindingContraceptionContext;
import doyenm.zooshell.common.function.FindingContraceptionFunction;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithContraceptionCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final LsWithCriteriaParser parser;
    private final FindingContraceptionFunction findingContraceptionFunction;

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")", ",");

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        if (context.getContraceptionExpression() != null
                && !context.getContraceptionExpression().isEmpty()
                && context.getContraception() != null) {
            context.getContraception().addAll(parser.parse(context.getContraceptionExpression(), excluded));
            context.setContraceptionExpression(parser.replaceGrammaticalExpression(context.getContraceptionExpression()));
            retrieveContraception(context);
            if (context.getConvertedContraception() != null) {
                return context.getContraception().size() == context.getConvertedContraception().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrieveContraception(LsWithCriteriaContext t) {
        t.getDiets()
                .stream()
                .map(FindingContraceptionContext::new)
                .map(findingContraceptionFunction)
                .filter(context -> context.getConvertedContraception() != null)
                .forEach(e ->
                        t.getConvertedContraception().put(e.getContraception(), e.getConvertedContraception()));
        return t;
    }
}
