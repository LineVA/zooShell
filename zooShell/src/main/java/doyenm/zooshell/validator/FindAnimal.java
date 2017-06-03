package doyenm.zooshell.validator;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindAnimal {

    FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();

    public Animal find(Zoo zoo, String padName) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(zoo.getAnimals(), padName);
        return Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal();
    }
}
