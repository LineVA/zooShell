package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalChangePaddockValidator implements Predicate<AnimalChangePaddockContext> {

    private final FindAnimal findAnimal;
    private final FindPaddock findPaddock;

    @Override
    public boolean test(AnimalChangePaddockContext t) {
        AnimalChangePaddockContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        Animal animal = findAnimal.find(context.getZoo(), context.getAnimal());
        if(pad != null && animal != null){
            context.setConvertedAnimal(animal);
            context.setConvertedPaddock(pad);
            return true;
        }
        return false;
    }

}
