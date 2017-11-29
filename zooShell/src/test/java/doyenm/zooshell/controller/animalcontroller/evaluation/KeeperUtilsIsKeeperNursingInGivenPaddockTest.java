package doyenm.zooshell.controller.animalcontroller.evaluation;

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
public class KeeperUtilsIsKeeperNursingInGivenPaddockTest {

    private AnimalKeeper givenKeeperWithOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(occupations);
        return keeper;
    }

    @Test
    public void shouldReturnTrueWhenTheKeeperIsNursingInThePaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.NURSING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperNursingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheKeeperIsNursingButNotInThePaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Paddock paddock2= Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.NURSING)
                .time(0.1)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperNursingInGivenPaddock(keeper, paddock2);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheKeeperIsInThePaddockButNotNursing() {
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
        boolean result = utils.isKeeperNursingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheKeeperIsNursingInThePaddockButWithATimeToZero() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.NURSING)
                .time(0.0)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        boolean result = utils.isKeeperNursingInGivenPaddock(keeper, paddock);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
