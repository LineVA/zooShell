package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalValidator implements Predicate<AnimalContext>{
FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();
    @Override
    public boolean test(AnimalContext t) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal());
        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
       if(t.getConvertedAnimal()!= null){
           return t.getConvertedAnimal().getPaddock().getEntry() != null;
       }
       return false;
    }
}