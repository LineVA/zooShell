package doyenm.zooshell.evaluation.keeper;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.offset;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationTaskControllerApplyTest {

    private AnimalKeeper givenKeeperWithTaskMap(Map<TaskType, Double> map) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getTaskEvaluations()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(keeper).setTaskEvaluations(Mockito.anyMap());
        keeper.setTaskEvaluations(map);
        return keeper;
    }

    private KeeperEvaluationContext givenContextWithOccupationsAndKeeper(List<TimedOccupation> occs, AnimalKeeper keeper) {
        KeeperEvaluationContext context = Mockito.mock(KeeperEvaluationContext.class);
        Mockito.when(context.getKeeper()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setKeeper(Mockito.any(AnimalKeeper.class));
        context.setKeeper(keeper);
        Mockito.when(context.getOccupations()).thenReturn(occs);
        Mockito.when(context.getTaskEvaluationMap()).thenCallRealMethod();
        return context;
    }

    @Test
    public void shoulSetTheCompetenceInCleaningToZeroWhenItIsInitiallyZeroAndTheKeeperDoesNotPractice() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.MEDICAL_TRAINING)
                .time(time1)
                .build();
        Map<TaskType, Double> map = new HashMap<>();
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNull();

    }

    @Test
    public void shoulSetTheCompetenceInCleaningToXWhenItIsInitiallyZeroAndTheKeeperPracticesForXTime() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double init = 0.0;
        Map<TaskType, Double> map = new HashMap<>();
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(time1 + init);
    }

    @Test
    public void shoulSetTheCompetenceInCleaningToXWhenItIsInitiallyZeroAndTheKeeperPracticeXTimeInTwoPaddocks() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Paddock pad2 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.3;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.CLEANING)
                .time(time2)
                .build();
        double init = 0.0;
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, init);
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1, occ2), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(init + time1 + time2);
    }

    @Test
    public void shoulSetTheCompetenceInCleaningToXWhenItIsInitiallyXAndTheKeeperDoesNotPractice() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.MEDICAL_TRAINING)
                .time(time1)
                .build();
        double init = 0.5;
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, init);
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(init);
    }

    @Test
    public void shoulSetTheCompetenceInCleaningToZWhenItIsInitiallyXAndTheKeeperPracticeYTime() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double init = 0.4;
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, init);
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(init + time1);
    }

    @Test
    public void shoulSetTheCompetenceInCleaningToZWhenItIsInitiallyXAndTheKeeperPracticeYTimeInTwoPaddocks() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Paddock pad2 = Paddock.builder().build();
        double time1 = 0.50;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.30;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.CLEANING)
                .time(time2)
                .build();
        double init = 0.10;
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, init);
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1, occ2), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        double result = init + time1 + time2;
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(result, offset(0.01));
    }
    
    @Test
    public void shoulSetTheCompetenceInCleaningAndFeedingToXAndYWhenTheKeeperPractices() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Paddock pad2 = Paddock.builder().build();
        double time1 = 0.50;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.30;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.FEEDING)
                .time(time2)
                .build();
        double init = 0.10;
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, init);
        AnimalKeeper keeper = givenKeeperWithTaskMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAndKeeper(Arrays.asList(occ1, occ2), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.CLEANING)).isEqualTo(init + time1);
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.FEEDING)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTaskEvaluations().get(TaskType.FEEDING)).isEqualTo(time2);
    }
}
