package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicatesIsDeadByHungerTest {

    private Animal givenAnimalWithHungerDays(int days) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getDaysOfHunger()).thenReturn(days);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalIsStarvingForThreeTurns() {
        // Given
        Animal animal = givenAnimalWithHungerDays(3);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByHunger(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalIsStarvingForFourTurns() {
        // Given
        Animal animal = givenAnimalWithHungerDays(4);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByHunger(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsStarvingForTwoTurns() {
        // Given
        Animal animal = givenAnimalWithHungerDays(2);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates(3, RandomUtils.nextDouble(), RandomUtils.nextDouble());
        // When
        boolean result = predicate.isDeadByHunger(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
