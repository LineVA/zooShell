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
public class AnimalDyingPredicatesIsDyingByFastTest {

      private FoodAttributes givenFoodAttributesWithFast(int fast) {
        FoodAttributes attributes = Mockito.mock(FoodAttributes.class);
        Mockito.when(attributes.getFastDays()).thenReturn(fast);
        return attributes;
    }
    
    private Animal givenAnimalWithFoodAttributes(FoodAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCurrentFoodAttributes()).thenReturn(attributes);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalHasSevenFastDaysPerWeek() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithFast(7);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFast(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnTrueWhenTheAnimalHasMoreThanSevenFastDaysPerWeek() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithFast(8);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFast(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenTheAnimalHasLessThanSevenFastDaysPerWeek() {
        // Given
        FoodAttributes attributes = givenFoodAttributesWithFast(6);
        Animal animal = givenAnimalWithFoodAttributes(attributes);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByFast(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
