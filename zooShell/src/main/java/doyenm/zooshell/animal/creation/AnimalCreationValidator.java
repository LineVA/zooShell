package doyenm.zooshell.animal.creation;

import doyenm.zooshell.model.Sex;
import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.common.context.FindingSexContext;
import doyenm.zooshell.common.context.FindingSpecieContext;
import doyenm.zooshell.common.function.FindingSexFunction;
import doyenm.zooshell.common.function.FindingSpecieFunction;
import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalCreationValidator implements Predicate<AnimalCreationContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();
    FindingSpecieFunction findingSpecieFunction = new FindingSpecieFunction();
    FindingSexFunction findingSexFunction = new FindingSexFunction();
    private final FindPaddock findPaddock;
    private final int maxTurnsOfUnusableState;

    private final int maxLengthName;

    @Override
    public boolean test(AnimalCreationContext t) {
        AnimalCreationContext context = t;
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), maxLengthName);
        result &= this.uniquenessNamesBiPredicates.test(context.getName().toUpperCase(), context.getAnimals().keySet());
        FindingSpecieContext findingSpecieContext = new FindingSpecieContext(context.getSpecies(), context.getSpecieName());
        FindingSexContext findingSexContext = new FindingSexContext(context.getSexName());
        context.setSpecie( Stream.of(findingSpecieContext)
                .map(findingSpecieFunction)
                .findFirst()
                .orElseGet(null)
                .getSpecie());
        t.setPaddock(findPaddock.find(t.getZoo(), t.getPaddockName()));
        context.setSex(Stream.of(findingSexContext)
                .map(findingSexFunction)
                .findFirst()
                .orElseGet(null)
                .getSex());
        if (context.getSpecie() != null && context.getPaddock() != null && context.getSex() != null) {
            return Sex.UNKNOWN != context.getSex() && result && context.getPaddock().getEntry() != null
                    && context.getPaddock().getTurnsOfUnusableState() < maxTurnsOfUnusableState;
        }
        return false;
    }
}
