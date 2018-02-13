package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicatesIsDeadByDrowningTest {

    private Animal givenAnimalWithDrowningDays(int days){
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getDaysOfDrowning()).thenReturn(days);
        return animal;
    }
    
    @Test
    public void shouldReturnTrueWhenTheAnimalIsDrowningForThreeTurns() {
        // Given
        Animal animal = givenAnimalWithDrowningDays(3);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByDrowning(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnTrueWhenTheAnimalIsDrowningForFourTurns() {
        // Given
        Animal animal = givenAnimalWithDrowningDays(4);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByDrowning(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnFalseWhenTheAnimalIsDrowningForTwoTurns() {
        // Given
        Animal animal = givenAnimalWithDrowningDays(2);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
