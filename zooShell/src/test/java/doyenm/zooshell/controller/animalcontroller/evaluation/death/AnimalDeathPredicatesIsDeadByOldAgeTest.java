package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.LifespanAttributes;
import doyenm.zooshell.model.Sex;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicatesIsDeadByOldAgeTest {

    private Animal givenAnimalWithAgeLifespanAndWellBeing(int age, int lifespan, double wb) {
        Animal animal = Mockito.mock(Animal.class);
        when(animal.getAge()).thenReturn(age);
        LifespanAttributes attributes = Mockito.mock(LifespanAttributes.class);
        when(attributes.getLifespanGivenSex(Mockito.any(Sex.class))).thenReturn(lifespan);
        when(animal.getLifespanAttributes()).thenReturn(attributes);
        when(animal.getWellBeing()).thenReturn(wb);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalIsOlderThanItsLifespanAndIsNotEnoughHappy() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 5, 1);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheAnimalIsOlderThanItsLifespanIsJustEnoughHappyButHasNoChance() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 5, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, 1.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheAnimalIsOlderThanItsLifespanIsJustEnoughHappyAndHasEnoughChance(){
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 5, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, -0.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnTrueWhenTheAnimalIsOlderThanItsLifespanIsEnoughHappyButHasNoChance() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 5, 10);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, 1.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheAnimalIsOlderThanItsLifespanIsEnoughHappyAndHasEnoughChance(){
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 5, 10);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, -0.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheAnimalHasTheAgeOfItsLifespanAndIsNotEnoughHappy() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(5, 5, 1);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheAnimalHasTheAgeOfItsLifespanIsJustEnoughHappyButHasNoChance() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(5, 5, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, 1.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheAnimalHasTheAgeOfItsLifespanIsJustEnoughHappyAndHasEnoughChance(){
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(5, 5, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, -0.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
       @Test
    public void shouldReturnTrueWhenTheAnimalHasTheAgeOfItsLifespanIsEnoughHappyButHasNoChance() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(5, 5, 10);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, 1.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheAnimalHasTheAgeOfItsLifespanIsEnoughHappyAndHasEnoughChance(){
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(5, 5, 10);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), 0.5, -0.1);
        // When
        boolean result = predicate.isDeadByOldAge(animal, 10.0);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsYoungerThanItsLifespan() {
        // Given
        Animal animal = givenAnimalWithAgeLifespanAndWellBeing(10, 15, RandomUtils.nextDouble());
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(RandomUtils.nextInt(), RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByOldAge(animal, RandomUtils.nextDouble());
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
