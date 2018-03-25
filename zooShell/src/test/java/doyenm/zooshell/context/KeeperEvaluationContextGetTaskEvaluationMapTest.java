package doyenm.zooshell.context;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetTaskEvaluationMapTest {

    @Test
    public void shouldReturnTheTaskEvaluationMapOfTheKeeper() {
        // Given
        Map<TaskType, Double> evaluations
                = new ImmutableMap.Builder<TaskType, Double>()
                        .put(TaskType.ENRICHMENT, 2.1)
                        .build();
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        when(keeper.getTaskEvaluations()).thenReturn(evaluations);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class),
                keeper);
        // When
        Map<TaskType, Double> actualEvaluations = subject.getTaskEvaluationMap();
        // Then
        Assertions.assertThat(actualEvaluations).hasSize(1);
        Assertions.assertThat(actualEvaluations.get(TaskType.ENRICHMENT)).isEqualTo(2.1);
    }

}
