package doyenm.zooshell.animal.rename;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.FindAnimal;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalRenameValidator implements Predicate<AnimalRenameContext> {

    private final FindAnimal findAnimal;
    private final NameValidator nameValidator;

    @Override
    public boolean test(AnimalRenameContext t) {
        AnimalRenameContext context = t;
        Animal animal = findAnimal.find(context.getZoo(), context.getCurrentName());
        if (animal == null) {
            return false;
        }
        context.setConvertedAnimal(animal);
        return nameValidator.test(
                NameDto.builder()
                        .testing(context.getNewName())
                        .existingNames(context.getAnimals())
                        .build()
        );
    }
}