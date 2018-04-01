package doyenm.zooshell.evaluation.animal;

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
public class KeeperUtilsTimeSpentDoingTheTaskInThePaddockTest {

    private AnimalKeeper givenKeeperWithOccupations(List<TimedOccupation> occupations) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(occupations);
        return keeper;
    }

    @Test
    public void shouldReturnTheTimeSpentWhenThereIsAnOccupationForTheKeeperWithTheTaskAndPaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Double time = 0.1;
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(time)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        Double result = utils.timeSpentDoingTheTaskInThePaddock(keeper, TaskType.FEEDING, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(time);
    }

    @Test
    public void shouldReturnZeroWhenThereIsAnOccupationForTheKeeperWithTheTaskButNotThePaddock() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Paddock paddock2 = Mockito.mock(Paddock.class);
        Double time = 0.1;
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(time)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        Double result = utils.timeSpentDoingTheTaskInThePaddock(keeper, TaskType.FEEDING, paddock2);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(0.0);
    }
    
     @Test
    public void shouldReturnZeroWhenThereIsAnOccupationForTheKeeperInthePaddockButNotTheExpectedTask() {
        // Given
        Paddock paddock = Mockito.mock(Paddock.class);
        Double time = 0.1;
        TimedOccupation occupation = TimedOccupation.builder()
                .paddock(paddock)
                .taskType(TaskType.FEEDING)
                .time(time)
                .build();
        AnimalKeeper keeper = givenKeeperWithOccupations(Arrays.asList(occupation));
        KeeperUtils utils = new KeeperUtils();
        // When
        Double result = utils.timeSpentDoingTheTaskInThePaddock(keeper, TaskType.CLEANING, paddock);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(0.0);
    }
}
