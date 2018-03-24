package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.context.FindingSexContext;
import doyenm.zooshell.validator.context.FindingSpecieContext;
import doyenm.zooshell.validator.function.FindingSexFunction;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

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
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(context.getPaddocks(), context.getPaddockName());
        FindingSexContext findingSexContext = new FindingSexContext(context.getSexName());
        context.setSpecie(Stream.of(findingSpecieContext)
                .map(findingSpecieFunction)
                .findFirst()
                .get()
                .getSpecie());
        t.setPaddock(findPaddock.find(t.getZoo(), t.getPaddockName()));
        context.setSex(Stream.of(findingSexContext)
                .map(findingSexFunction)
                .findFirst()
                .get()
                .getSex());
        if (context.getSpecie() != null && context.getPaddock() != null && context.getSex() != null) {
            return Sex.UNKNOWN != context.getSex() && result && context.getPaddock().getEntry() != null
                    && context.getPaddock().getTurnsOfUnusableState() < maxTurnsOfUnusableState;
        }
        return false;
    }
}
