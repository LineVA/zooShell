package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalDetailsValidator implements Predicate<AnimalDetailsContext>{
FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();

    @Override
    public boolean test(AnimalDetailsContext t) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal());

        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
       return t.getConvertedAnimal()!= null;
    }
}
