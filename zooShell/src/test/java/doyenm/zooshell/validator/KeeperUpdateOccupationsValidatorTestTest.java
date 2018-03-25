package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.model.*;
import doyenm.zooshell.validator.function.FindingTaskFunction;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class KeeperUpdateOccupationsValidatorTestTest {

    KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator(
            new FindKeeper(),
            new FindPaddock(),
            new FindingTaskFunction(),
            new DoubleValuesPredicates(),
            3
    );

    private Position givenPosition() {
        return Mockito.mock(Position.class);
    }

    private Paddock givenPaddockWithEntry(Position entry, int turns) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getEntry()).thenReturn(entry);
        Mockito.when(pad.getTurnsOfUnusableState()).thenReturn(turns);
        return pad;
    }

    private AnimalKeeper givenKeeperWithOccupationsList(List<TimedOccupation> list) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getOccupations()).thenReturn(list);
        return keeper;
    }

    private TimedOccupation givenOccupationWithPaddockTimeAndTask(Paddock pad, double time,
            TaskType task) {
        TimedOccupation occ = Mockito.mock(TimedOccupation.class);
        Mockito.when(occ.getTaskType()).thenReturn(task);
        Mockito.when(occ.getTime()).thenReturn(time);
        Mockito.when(occ.getPaddock()).thenReturn(pad);
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
        Mockito.when(context.getKeeper()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getPaddock()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getTask()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getTime()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.doCallRealMethod().when(context).setConvertedKeeper(Mockito.any(AnimalKeeper.class));
        Mockito.doCallRealMethod().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        Mockito.doCallRealMethod().when(context).setConvertedTask(Mockito.any(TaskType.class));
        Mockito.doCallRealMethod().when(context).setConvertedTime(Mockito.anyDouble());
        return context;
    }

    /**
     * Should return true: - the keeper exists - the paddock exists and has an
     * entry - the task exists - the task is not UNKNOWN - the time is a Double
     * - the time is greater or equals than 0.0 - the sum of the occupations of
     * the keeper is lower or equals than 100.0
     */
    @Test
    public void shouldReturnTrueWhenEveryThingIsCorrect() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheKeeperDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = null;
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
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
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockExistsButDoesNotHaveAnEntry() {
        // Given
        Position entry = null;
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockExistsButIsUnusableSinceThreeTurns() {
        // Given
        Position entry = null;
        Paddock pad = givenPaddockWithEntry(entry, 3);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockExistsButIsUnusableSinceMoreThanThreeTurns() {
        // Given
        Position entry = null;
        Paddock pad = givenPaddockWithEntry(entry, 4);
        TaskType task = TaskType.CLEANING;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 5.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTaskDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = null;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTaskIsUnknown() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.UNKNOWN;
        double time = 10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTimeIsNegativ() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = -10.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSumOfTimesIsGreaterThan100WithNoOccupationWithThisPaddockOrTask() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        Paddock pad2 = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 100.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad2, 50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSumOfTimesIsGreaterThan100WithAnOccupationWithThisPaddockButNotWithThisTask() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 100.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 50.0, TaskType.ENRICHMENT);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSumOfTimesIsGreaterThan100WithAnOccupationWithThisTaskButNotWithThisPaddock() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        Paddock pad2 = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 100.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad2, 50.0, TaskType.CLEANING);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    /**
     * Should return true: - the keeper exists - the paddock exists and has an
     * entry - the task exists - the time is a Double - the time is greater or
     * equals than 0.0 - the sum of the occupations of the keeper is lower or
     * equals than 100.0
     */
    @Test
    public void shouldReturnTrueWhenTheSumOfTimesIsLowerOrEqualsThan100OnlyBecauseWeUpdateTheTimeOfAnAlreadyExistingOccupation() {
        // Given
        Position entry = givenPosition();
        Paddock pad = givenPaddockWithEntry(entry, 2);
        TaskType task = TaskType.CLEANING;
        double time = 100.0;
        TimedOccupation occupation = givenOccupationWithPaddockTimeAndTask(pad, 50.0, TaskType.CLEANING);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occupation);
        AnimalKeeper keeper = givenKeeperWithOccupationsList(list);
        Zoo zoo = givenZoo();
        KeeperUpdateOccupationsContext context = givenContextWithZooPaddockTaskTimeAndKeeper(zoo, pad, task, time, keeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
