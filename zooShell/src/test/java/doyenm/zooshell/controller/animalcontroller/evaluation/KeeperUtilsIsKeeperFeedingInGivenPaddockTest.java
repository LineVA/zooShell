package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class KeeperUtilsIsKeeperFeedingInGivenPaddockTest {

    private AnimalKeeper givenKeeperWithOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(occupations);
        return keeper;
    }

    @Test
    public void shouldReturnTrueWhenTheKeeperIsFeedingInThePaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperFeedingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheKeeperIsFeedingButNotInThePaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Paddock paddock2= Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperFeedingInGivenPaddock(keeper, paddock2);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheKeeperIsInThePaddockButNotFeeding() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.CLEANING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperFeedingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheKeeperIsFeedingInThePaddockButWithATimeToZero() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(0.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperFeedingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
