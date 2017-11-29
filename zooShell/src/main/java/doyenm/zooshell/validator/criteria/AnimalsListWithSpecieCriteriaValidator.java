package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.validator.context.FindingSexContext;
import doyenm.zooshell.validator.context.FindingSpecieContext;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithSpecieCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final LsWithCriteriaParser parser;
    private final FindingSpecieFunction findingSpecie;

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")", ",");

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;

        if (context.getSpeciesExpression()!= null && !context.getSpeciesExpression().isEmpty() && context.getSpecies()!= null) {
            context.getSpecies().addAll(parser.parse(context.getSpeciesExpression(), excluded));
            context.setSpeciesExpression(parser.replaceGrammaticalExpression(context.getSpeciesExpression()));
            context = retrieveSpecies(context);
            if (context.getConvertedSpecies()!= null) {
                return context.getSpecies().size() == context.getConvertedSpecies().size();
            }
            return false;
        }
        return true;
    }

    private LsWithCriteriaContext retrieveSpecies(LsWithCriteriaContext t) {
        t.getSpecies()
                .stream()
                .map((String t1) -> new FindingSpecieContext(t.getZoo().getSpecies(), t1))
                .map(findingSpecie)
                .filter(e -> e.getSpecie()!= null)
                .forEach(e -> {
                    t.getConvertedSpecies().put(e.getSpecieName(), e.getSpecie());
                });
        return t;
    }
}
