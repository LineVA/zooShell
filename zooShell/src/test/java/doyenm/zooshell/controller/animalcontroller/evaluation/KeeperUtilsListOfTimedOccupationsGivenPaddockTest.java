package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperUtilsListOfTimedOccupationsGivenPaddockTest {

    private AnimalKeeper givenKeeperWithOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(occupations);
        return keeper;
    }

    @Test
    public void shouldReturnAListOfTwoElementsWhenTheKeeperHasTwoOccupationsInThisPaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Paddock paddock2 = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        TimedOccupation occupation2 = TimedOccupation.builder()
                .paddock(paddock2)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        TimedOccupation occupation3 = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation, occupation2, occupation3));
        KeeperUtils utils = new KeeperUtils();
        // When
        List<TimedOccupation> result = utils.listOfTimedOccupationsInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0)).isEqualTo(occupation);
        Assertions.assertThat(result.get(1)).isEqualTo(occupation3);
    }

    @Test
    public void shouldReturnAListOfTwoElementsWhenTheKeeperHasThreeOccupationsInThisPaddockButOnlyTwoWithANotNullTime() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        TimedOccupation occupation2 = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.0)
                .build();
        TimedOccupation occupation3 = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation, occupation2, occupation3));
        KeeperUtils utils = new KeeperUtils();
        // When
        List<TimedOccupation> result = utils.listOfTimedOccupationsInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0)).isEqualTo(occupation);
        Assertions.assertThat(result.get(1)).isEqualTo(occupation3);
    }
    
    @Test
    public void shouldReturnAnEmptyListWhenNoOccupationIsCorrect() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.0)
                .build();
        TimedOccupation occupation2 = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.0)
                .build();
        TimedOccupation occupation3 = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation, occupation2, occupation3));
        KeeperUtils utils = new KeeperUtils();
        // When
        List<TimedOccupation> result = utils.listOfTimedOccupationsInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEmpty();
    }
    
       @Test
    public void shouldReturnAnEmptyListWhenTheKeeperHasNoOccupation() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        AnimalKeeper keeper = givenKeeperWithOccupations(new ArrayList());
        KeeperUtils utils = new KeeperUtils();
        // When
        List<TimedOccupation> result = utils.listOfTimedOccupationsInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEmpty();
    }

}
