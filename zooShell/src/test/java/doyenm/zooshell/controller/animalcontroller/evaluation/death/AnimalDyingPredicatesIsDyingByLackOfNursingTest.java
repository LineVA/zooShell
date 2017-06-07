package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicatesIsDyingByLackOfNursingTest {

    private AnimalKeeper givenKeeperWithTimedOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper attributes = Mockito.mock(AnimalKeeper.class);
        Mockito.when(attributes.getOccupations()).thenReturn(occupations);
        return attributes;
    }

    private Animal givenAnimalWithPaddockAndNursing(Paddock pad, boolean nursing) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        Mockito.when(animal.isNeedWeaningByHumans()).thenReturn(nursing);
        return animal;
    }

    private Paddock givenPaddock() {
        return Mockito.mock(Paddock.class);
    }

    @Test
    public void shouldReturnFalseWhenThereIsAtLeastOneToNurseThePaddockWhenTheAnimalNeedIt() {
        // Given
        Paddock pad = givenPaddock();
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad)
                .taskType(TaskType.NURSING)
                .time(1.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithTimedOccupations(Arrays.asList(occ1));
        Animal animal = givenAnimalWithPaddockAndNursing(pad, true);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByLackOfNursing(animal, Arrays.asList(keeper));
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenThereIsNoOneToNurseThePaddockWhenTheAnimalNeedIt() {
        // Given
        Paddock pad = givenPaddock();
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad)
                .taskType(TaskType.CLEANING)
                .time(1.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithTimedOccupations(Arrays.asList(occ1));
        Animal animal = givenAnimalWithPaddockAndNursing(pad, true);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByLackOfNursing(animal, Arrays.asList(keeper));
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsNoOneToNurseThePaddockButTheAnimalDoesNotNeedIt() {
        // Given
        Paddock pad = givenPaddock();
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad)
                .taskType(TaskType.CLEANING)
                .time(1.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithTimedOccupations(Arrays.asList(occ1));
        Animal animal = givenAnimalWithPaddockAndNursing(pad, false);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByLackOfNursing(animal, Arrays.asList(keeper));
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
