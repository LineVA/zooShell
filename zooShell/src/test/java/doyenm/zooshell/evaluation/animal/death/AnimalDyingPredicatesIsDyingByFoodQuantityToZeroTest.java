package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicatesIsDyingByFoodQuantityToZeroTest {

      private FoodAttributes givenFoodAttributesWithQuantity(double quantity) {
        FoodAttributes attributes = Mockito.mock(FoodAttributes.class);
        Mockito.when(attributes.getQuantity()).thenReturn(quantity);
        return attributes;
    }
    
    private Animal givenAnimalWithFoodAttributes(FoodAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCurrentFoodAttributes()).thenReturn(attributes);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalHasAQuantityToZero() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithQuantity(0.0);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFoodQuantityToZero(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnTrueWhenTheAnimalHasLessThanZeroKgPerDay() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithQuantity(-0.01);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFoodQuantityToZero(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenTheAnimalHasMoreThanZeroKgPerDay() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithQuantity(0.01);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFoodQuantityToZero(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
