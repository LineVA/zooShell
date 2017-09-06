package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityContextBuildTest {

    private FoodAttributes givenFoodAttributes(){
        FoodAttributes attributes = Mockito.mock(FoodAttributes.class);
        Mockito.when(attributes.getQuantity()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(attributes).setQuantity(Mockito.any(Double.class));
        return attributes;
    }
    
    private Animal givenAnimalWithNameAndFoodAttributes(String name, FoodAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCurrentFoodAttributes()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(animal).setCurrentFoodAttributes(Mockito.any(FoodAttributes.class));
        animal.setCurrentFoodAttributes(new FoodAttributes());
        Mockito.when(animal.getName()).thenReturn(name);
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

    private AnimalUpdateFoodQuantityContext givenContextWithZooAnimalAndFoodQuantity(
            Zoo zoo, Animal animal, Double quantity) {
        AnimalUpdateFoodQuantityContext context = Mockito.mock(AnimalUpdateFoodQuantityContext.class);
        Mockito.doCallRealMethod().when(context).build();
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getConvertedFoodQuantity()).thenReturn(quantity);
        return context;
    }

    @Test
    public void shouldReplaceTheFoodQuantityAnimalByTheOneSpecified() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        FoodAttributes attributes = givenFoodAttributes();
        Animal animal = givenAnimalWithNameAndFoodAttributes(name, attributes);
        Double newFoodQuantity = TestUtils.generateDouble();
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, newFoodQuantity);
        // When
        context.build();
        // Then
        Assertions.assertThat(context.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(context.getZoo().getAnimals().size()).isEqualTo(1);
        Assertions.assertThat(context.getZoo().getAnimals().get(name).getCurrentFoodAttributes().getQuantity()).isEqualTo(newFoodQuantity);
    }
}
