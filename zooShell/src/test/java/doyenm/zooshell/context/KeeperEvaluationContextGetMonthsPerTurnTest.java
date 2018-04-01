package doyenm.zooshell.context;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetMonthsPerTurnTest {

    @Test
    public void shouldReturnTheNumberOfMonthsPerTurnOfTheContext() {
        // Given
        int months = RandomUtils.nextInt();
        Zoo zoo = mock(Zoo.class);
        when(zoo.getMonthsPerEvaluation()).thenReturn(months);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(zoo,
                mock(AnimalKeeper.class));
        // When
        int actualMonths = subject.getMonthsPerTurn();
        // Then
        Assertions.assertThat(actualMonths).isEqualTo(months);
    }

}
