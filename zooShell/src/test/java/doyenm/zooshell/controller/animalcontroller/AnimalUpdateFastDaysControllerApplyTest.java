package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.anyList;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietControllerApplyTest {

    private AnimalUpdateDietController subject;

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

    private AnimalUpdateDietContext givenContext(Zoo zoo, Animal animal, List<Diet> diets) {
        AnimalUpdateDietContext context = Mockito.mock(AnimalUpdateDietContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.diffLists()).thenReturn(diets);
        return context;
    }

    @Test
    public void shouldReplaceTheDietsOfTheAnimal() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimal(name, Diet.FOLIVOROUS);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalUpdateDietContext context = givenContext(zoo, animal, Arrays.asList(Diet.CARNIVOROUS, Diet.NECTARIVOROUS));
        subject = new AnimalUpdateDietController();
        // When
        AnimalUpdateDietContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals()
                .get(name.toUpperCase()).getDiets()).isEqualTo(Arrays.asList(Diet.CARNIVOROUS, Diet.NECTARIVOROUS));

    }
}
