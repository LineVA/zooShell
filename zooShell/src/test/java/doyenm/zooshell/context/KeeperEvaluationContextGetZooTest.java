package doyenm.zooshell.context;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetZooTest {

    @Test
    public void shouldReturnTheZooOfTheContext() {
        // Given
        Zoo zoo = mock(Zoo.class);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(zoo,
                mock(AnimalKeeper.class));
        // When
        Zoo actualZoo = subject.getZoo();
        // Then
        Assertions.assertThat(actualZoo).isEqualTo(zoo);
    }

}
