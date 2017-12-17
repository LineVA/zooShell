package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalChangeNameValidator implements Predicate<AnimalChangeNameContext> {

    private final FindAnimal findAnimal;
    private final NameValidator nameValidator;

    @Override
    public boolean test(AnimalChangeNameContext t) {
        AnimalChangeNameContext context = t;
        Animal animal = findAnimal.find(context.getZoo(), context.getCurrentName());
        if (animal == null) {
            return false;
        }
        context.setConvertedAnimal(animal);
        return nameValidator.test(NameDto.builder()
                .testing(context.getNewName())
                .exitingNames(context.getAnimals())
                .build());
    }
}
