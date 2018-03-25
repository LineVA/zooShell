package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysControllerApplyTest {

    private AnimalUpdateFastDaysController subject;

    private Animal givenAnimal(String name, int days) {
        FoodAttributes att = mock(FoodAttributes.class);
        when(att.getFastDays()).thenCallRealMethod();
        doCallRealMethod().when(att).setFastDays(anyInt());
        
        Animal animal = Mockito.mock(Animal.class);
        doCallRealMethod().when(animal).setCurrentFoodAttributes(any());
        when(animal.getCurrentFoodAttributes()).thenCallRealMethod();
        animal.setCurrentFoodAttributes(att);
        
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

    private AnimalUpdateFastDaysContext givenContext(Zoo zoo, Animal animal, int newFastDays) {
        AnimalUpdateFastDaysContext context = Mockito.mock(AnimalUpdateFastDaysContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        when(context.getConvertedFastDays()).thenReturn(newFastDays);
        String name = animal.getName();
        when(context.getAnimal()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldReplaceTheNumberOfFastDaysOfTheAnimal() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimal(name, RandomUtils.nextInt());
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        int newFastDays = RandomUtils.nextInt();
        AnimalUpdateFastDaysContext context = givenContext(zoo, animal, newFastDays);
        subject = new AnimalUpdateFastDaysController();
        // When
        AnimalUpdateFastDaysContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals()
                .get(name.toUpperCase()).getCurrentFoodAttributes().getFastDays())
                .isEqualTo(newFastDays);

    }
}
