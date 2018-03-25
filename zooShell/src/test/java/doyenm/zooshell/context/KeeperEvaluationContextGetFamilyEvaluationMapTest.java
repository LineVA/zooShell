package doyenm.zooshell.context;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
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
public class KeeperEvaluationContextGetFamilyEvaluationMapTest {

    @Test
    public void shouldReturnTheFamilyEvaluationMapOfTheKeeper() {
        // Given
        Map<Family, Double> evaluations
                = new ImmutableMap.Builder<Family, Double>()
                        .put(Family.LEMURIDAE, 2.1)
                        .build();
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        when(keeper.getFamilyEvaluations()).thenReturn(evaluations);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class),
                keeper);
        // When
        Map<Family, Double> actualEvaluations = subject.getFamilyEvaluationMap();
        // Then
        Assertions.assertThat(actualEvaluations).hasSize(1);
        Assertions.assertThat(actualEvaluations.get(Family.LEMURIDAE)).isEqualTo(2.1);
    }

}
