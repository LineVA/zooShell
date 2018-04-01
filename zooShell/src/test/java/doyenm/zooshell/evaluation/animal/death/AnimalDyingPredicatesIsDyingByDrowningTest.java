package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicatesIsDyingByDrowningTest {

      private Paddock givenPaddockWithType(PaddockType type) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getType()).thenReturn(type);
        return pad;
    }
    
    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    @Test
    public void shouldReturnTrueWhenThePaddockTypeIsAquarium() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.AQUARIUM);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenThePaddockTypeIsAviary() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.AVIARY);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsContact() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.CONTACT);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsIsland() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.ISLAND);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsLowland() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.LOWLAND);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsPit() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.PIT);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsUnknown() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.UNKNOWN);
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockTypeIsVivarium() {
        // Given
        Paddock pad = givenPaddockWithType(PaddockType.VIVARIUM
        );
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByDrowning(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
