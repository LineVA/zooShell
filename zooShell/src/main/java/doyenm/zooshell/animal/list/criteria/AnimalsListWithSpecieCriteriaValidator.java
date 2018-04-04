package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.context.FindingSpecieContext;
import doyenm.zooshell.common.function.FindingSpecieFunction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalsListWithSpecieCriteriaValidator implements Predicate<LsWithCriteriaContext> {

    private final List<String> excluded;

    private final LsWithCriteriaParser parser;
    private final FindingSpecieFunction findingSpecie;

    @Override
    public boolean test(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;

        if (context.getSpeciesExpression()!= null && !context.getSpeciesExpression().isEmpty() && context.getSpecies()!= null) {
            context.getSpecies().addAll(parser.parse(context.getSpeciesExpression(), excluded));
            context.setSpeciesExpression(parser.replaceGrammaticalExpression(context.getSpeciesExpression()));
            retrieveSpecies(context);
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
                .map(t1 -> new FindingSpecieContext(t.getZoo().getSpecies(), t1))
                .map(findingSpecie)
                .filter(e -> e.getSpecie()!= null)
                .forEach(e ->
                    t.getConvertedSpecies().put(e.getSpecieName(), e.getSpecie())
                );
        return t;
    }
}
