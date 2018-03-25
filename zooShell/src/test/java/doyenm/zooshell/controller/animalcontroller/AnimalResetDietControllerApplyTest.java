package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalResetDietControllerApplyTest {

    private AnimalResetDietController subject;

    private Animal givenAnimal(String name, Diet diet) {
        Animal animal = Mockito.mock(Animal.class);
        doCallRealMethod().when(animal).setDiets(anyList());
        when(animal.getDiets()).thenCallRealMethod();
        animal.setDiets(Arrays.asList(diet));
        when(animal.getName()).thenReturn(name);
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

    private AnimalContext givenContext(Zoo zoo, Animal animal, List<Diet> diets) {
        AnimalContext context = Mockito.mock(AnimalContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        String name = animal.getName();
        when(context.getAnimal()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldReplaceTheDietsOfTheAnimal() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimal(name, Diet.FOLIVOROUS);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalContext context = givenContext(zoo, animal, Arrays.asList(Diet.CARNIVOROUS, Diet.NECTARIVOROUS));
        subject = new AnimalResetDietController();
        // When
        AnimalContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals()
                .get(name.toUpperCase()).getDiets()).isEqualTo(Arrays.asList(Diet.NONE));

    }
}
