package doyenm.zooshell.validator;

import doyenm.zooshell.context.SpecieDetailsContext;
import doyenm.zooshell.validator.context.FindingSpecieContext;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class SpecieDetailsValidator implements Predicate<SpecieDetailsContext>{
FindingSpecieFunction findingSpecieFunction = new FindingSpecieFunction();

    @Override
    public boolean test(SpecieDetailsContext t) {
        FindingSpecieContext findingSpecieContext = new FindingSpecieContext(t.getZoo().getSpecies(), t.getSpecieName());

        t.setSpecie(Stream.of(findingSpecieContext)
                .map(findingSpecieFunction)
                .findFirst()
                .orElseGet(null)
                .getSpecie());
       return t.getSpecie()!= null;
    }
}
