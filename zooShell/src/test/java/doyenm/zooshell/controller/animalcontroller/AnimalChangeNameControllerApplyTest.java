package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameControllerApplyTest {

    private Animal givenAnimalWithName(String name) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setName(anyString());
        Mockito.when(animal.getName()).thenCallRealMethod();
        animal.setName(name);
        return animal;
    }

    private Map<String, Animal> givenMapWithAnimal(Animal animal) {
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName().toUpperCase(), animal);
        return map;
    }

    private Zoo givenZooWithAnimals(Map<String, Animal> animals) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalChangeNameContext givenContext(Zoo zoo, Animal animal, String newName) {
        AnimalChangeNameContext context = Mockito.mock(AnimalChangeNameContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getNewName()).thenReturn(newName);
        String name = animal.getName();
        when(context.getCurrentName()).thenReturn(name);

        return context;
    }

    @Test
    public void shouldReplaceTheNameOfTheAnimalByTheOneSpecified() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimalWithName(name);
        String newName = RandomStringUtils.randomAlphabetic(10);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalChangeNameContext context = givenContext(zoo, animal, newName);
        AnimalChangeNameController controller = new AnimalChangeNameController();
        // When
        AnimalChangeNameContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getAnimals().get(name.toUpperCase())).isNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals().get(newName.toUpperCase())).isNotNull();

    }
}
