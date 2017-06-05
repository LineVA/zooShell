package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Sex;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDeathPredicatesIsDeadByOldAgeTest {

    private Animal givenAnimalWithAgeAndLifespan(int age, int lifespan) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getMaturityGivenSex(Mockito.any(Sex.class))).thenReturn(lifespan);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalIsOlderThanItsLifespan() {
        // Given
        Animal animal = givenAnimalWithAgeAndLifespan(10, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates();
        // When
        boolean result = predicate.isDeadByOldAge(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheANimalIsTheSameAgeAsItsLifespan() {
        // Given
        Animal animal = givenAnimalWithAgeAndLifespan(5, 5);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates();
        // When
        boolean result = predicate.isDeadByOldAge(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheANimalIsYoungerThanItsLifespan() {
        // Given
        Animal animal = givenAnimalWithAgeAndLifespan(10, 15);
        AnimalDeathPredicates predicate = new AnimalDeathPredicates();
        // When
        boolean result = predicate.isDeadByOldAge(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
