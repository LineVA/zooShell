package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperUpdateOccupationsValidatorTestTest {

    private Position givenPosition() {
        return Mockito.mock(Position.class);
    }

    private Paddock givenPaddockWithEntry(Position entry) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getEntry()).thenReturn(entry);
        return pad;
    }

    private AnimalKeeper givenKeeperWithOccupationsList(List<TimedOccupation> list) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(list);
        return keeper;
    }

    private TimedOccupation givenOccupationWithTimeAndTask(double time, TaskType task) {
        TimedOccupation occ = Mockito.mock(TimedOccupation.class);
        Mockito.when(occ.getTaskType()).thenReturn(task);
        Mockito.when(occ.getTime()).thenReturn(time);
        return occ;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getKeepers()).thenReturn(new HashMap<>());
        Mockito.when(zoo.getPaddocks()).thenReturn(new HashMap<>());
        return zoo;
    }

    private KeeperUpdateOccupationsContext givenContextWithZooPaddockTaskTimeAndKeeper(Zoo zoo,
            Paddock pad, TaskType task, double time, AnimalKeeper keeper) {
        KeeperUpdateOccupationsContext context = Mockito.mock(KeeperUpdateOccupationsContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedKeeper()).thenReturn(keeper);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Mockito.when(context.getConvertedTask()).thenReturn(task);
        Mockito.when(context.getConvertedTime()).thenReturn(time);
        Mockito.when(context.getKeeper()).thenReturn(TestUtils.generateString());
        Mockito.when(context.getPaddock()).thenReturn(TestUtils.generateString());
        Mockito.when(context.getTask()).thenReturn(TestUtils.generateString());
        Mockito.when(context.getTime()).thenReturn(TestUtils.generateString());
        Mockito.doCallRealMethod().when(context).setConvertedKeeper(Mockito.any(AnimalKeeper.class));
        Mockito.doCallRealMethod().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        Mockito.doCallRealMethod().when(context).setConvertedTask(Mockito.any(TaskType.class));
        Mockito.doCallRealMethod().when(context).setConvertedTime(Mockito.anyDouble());
        return context;
    }

    /**
     * Should return true: - the keeper exists - the paddock exists and has an
     * entry - the task exists - the time is a Double - the time is greater or
     * equals than 0.0 - the sum of the occupations of the keeper is lower or
     * equals than 100.0
     *
     */
    @Test
    public void shouldReturnTrueWhenEveryThingIsCorrect() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

      @Test
    public void shouldReturnFalseWhenTheKeeperDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = null;
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        Paddock pad = null;
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

     @Test
    public void shouldReturnFalseWhenThePaddockExistsButDoesNotHaveAnEntry() {
        // Given
        Position entry = null;
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheTaskDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = null;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheTimeIsNegativ() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = TaskType.CLEANING;
        double time = -10.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheSumOfTimesIsGreaterThan100() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry);
        TaskType task = TaskType.CLEANING;
        double time = 100.0;
        TimedOccupation occupation = givenOccupationWithTimeAndTask(50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
