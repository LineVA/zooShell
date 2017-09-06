package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietContextBuildTest {

    private Animal givenAnimalWithName(String name) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setDiets(Mockito.anyList());
        Mockito.when(animal.getDiets()).thenCallRealMethod();
        Mockito.when(animal.getName()).thenReturn(name);
        animal.setDiets(new ArrayList<>());
        return animal;
    }

    private Map<String, Animal> givenMapWithAnimal(Animal animal) {
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName(), animal);
        return map;
    }

    private Zoo givenZooWithAnimals(Map<String, Animal> animals) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalUpdateDietContext givenContextWithZooAnimalAndDiets(Zoo zoo, Animal animal, List<Diet> diets) {
        AnimalUpdateDietContext context = Mockito.mock(AnimalUpdateDietContext.class);
        Mockito.doCallRealMethod().when(context).build();
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getConvertedDiets()).thenReturn(diets);
        return context;
    }

    @Test
    public void shouldReplaceTheDietAnimalByTheOneSpecified() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimalWithName(name);
        Diet newDiet = Diet.FOLIVOROUS;
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalUpdateDietContext context = givenContextWithZooAnimalAndDiets(zoo, animal, Arrays.asList(newDiet));
        // When
        context.build();
        // Then
        Assertions.assertThat(context.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(context.getZoo().getAnimals().size()).isEqualTo(1);
        Assertions.assertThat(context.getZoo().getAnimals().get(name).getDiets().size()).isEqualTo(1);
        Assertions.assertThat(context.getZoo().getAnimals().get(name).getDiets()).contains(newDiet);

    }

}
