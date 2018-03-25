package doyenm.zooshell.validator;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;

import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindAnimal {

    FindingAnimalWithEntryCheckFunction findingAnimalFunction = new FindingAnimalWithEntryCheckFunction();

    public Animal find(Zoo zoo, String name) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(zoo.getAnimals(), name.toUpperCase());
        return Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal();
    }
}
