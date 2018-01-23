package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityControllerApplyTest {

    private AnimalUpdateFoodQuantityController subject;

    private Animal givenAnimal(String name, int days) {
        FoodAttributes att = mock(FoodAttributes.class);
        when(att.getQuantity()).thenCallRealMethod();
        doCallRealMethod().when(att).setQuantity(anyDouble());
        
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

    private AnimalUpdateFoodQuantityContext givenContext(Zoo zoo, Animal animal, double newQuantity) {
        AnimalUpdateFoodQuantityContext context = Mockito.mock(AnimalUpdateFoodQuantityContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        when(context.getConvertedFoodQuantity()).thenReturn(newQuantity);
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
        double newQuantity = RandomUtils.nextDouble();
        AnimalUpdateFoodQuantityContext context = givenContext(zoo, animal, newQuantity);
        subject = new AnimalUpdateFoodQuantityController();
        // When
        AnimalUpdateFoodQuantityContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals()
                .get(name.toUpperCase()).getCurrentFoodAttributes().getQuantity())
                .isEqualTo(newQuantity);

    }
}
