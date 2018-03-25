package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Animal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class FindingAnimalContext {
    private final Map<String, Animal> animals;
    private final String AnimalName;
    @Setter
    private Animal animal;
}
