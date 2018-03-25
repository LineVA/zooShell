package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.context.FindingSexContext;
import doyenm.zooshell.validator.function.FindingSexFunction;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithSexCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final LsWithCriteriaParser parser;
    private final FindingSexFunction findingSexFunction;

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")", ",");

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        if (context.getSexesExpression()!= null && !context.getSexesExpression().isEmpty() && context.getSexes()!= null) {
            context.getSexes().addAll(parser.parse(context.getSexesExpression(), excluded));
            context.setSexesExpression(parser.replaceGrammaticalExpression(context.getSexesExpression()));
            retrieveSex(context);
            if (context.getConvertedSexes()!= null) {
                return context.getSexes().size() == context.getConvertedSexes().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrieveSex(LsWithCriteriaContext t) {
        t.getSexes()
                .stream()
                .map(FindingSexContext::new)
                .map(findingSexFunction)
                .filter(e -> e.getSex()!= null)
                .forEach(e ->
                    t.getConvertedSexes().put(e.getSexName(), e.getSex()
                ));
        return t;
    }
}
