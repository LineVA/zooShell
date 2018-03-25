package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicatesIsDyingByLackOfKeepersTest {

    private AnimalKeeper givenKeeperWithTimedOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper attributes = Mockito.mock(AnimalKeeper.class);
        Mockito.when(attributes.getOccupations()).thenReturn(occupations);
        return attributes;
    }

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private Paddock givenPaddock(){
        return Mockito.mock(Paddock.class);
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsAtLeastOneToFeedThePaddock() {
        // Given
        Paddock pad = givenPaddock();
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad)
                .taskType(TaskType.FEEDING)
                .time(1.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithTimedOccupations(Arrays.asList(occ1));
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByLackOfKeeper(animal, Arrays.asList(keeper));
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnTrueWhenThereIsNoOneToFeedThePaddock() {
        // Given
        Paddock pad = givenPaddock();
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad)
                .taskType(TaskType.CLEANING)
                .time(1.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithTimedOccupations(Arrays.asList(occ1));
        Animal animal = givenAnimalWithPaddock(pad);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByLackOfKeeper(animal, Arrays.asList(keeper));
        // Then
        Assertions.assertThat(result).isTrue();
    }

}
