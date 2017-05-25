package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.context.FindingSexContext;
import doyenm.zooshell.validator.context.FindingSpecieContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import doyenm.zooshell.validator.function.FindingSexFunction;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalCreationValidator implements Predicate<AnimalCreationContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();
    FindingSpecieFunction findingSpecieFunction = new FindingSpecieFunction();
    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();
    FindingSexFunction findingSexFunction = new FindingSexFunction();

    @Override
    public boolean test(AnimalCreationContext t) {
        AnimalCreationContext context = t;
        context.convert();
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(context.getName(), context.getZoo().getAnimals().keySet());
        FindingSpecieContext findingSpecieContext = new FindingSpecieContext(context.getZoo().getSpecies(), context.getSpecieName());
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(context.getZoo().getPaddocks(), context.getPaddockName());
        FindingSexContext findingSexContext = new FindingSexContext(context.getSexName());
        context.setSpecie(Stream.of(findingSpecieContext)
                .map(findingSpecieFunction)
                .findFirst()
                .get()
                .getSpecie());
        context.setPaddock(Stream.of(findingPaddockContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        context.setSex(Stream.of(findingSexContext)
                .map(findingSexFunction)
                .findFirst()
                .get()
                .getSex());
        if (context.getSpecie() != null && context.getPaddock() != null && context.getSex() != null) {
            return result && context.getPaddock().getEntry() != null;
        }
        return false;
    }
}
