package doyenm.zooshell.context;

import doyenm.zooshell.evaluation.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class PaddockEvaluationContextGetZooTest {

    @Test
    public void shouldReturnTheZooOfTheContext() {
        // Given
        Zoo zoo = mock(Zoo.class);
        PaddockEvaluationContext subject = new PaddockEvaluationContext(zoo, mock(Paddock.class));
        // When
        Zoo actualZoo = subject.getZoo();
        // Then
        Assertions.assertThat(actualZoo).isEqualTo(zoo);
    }
}