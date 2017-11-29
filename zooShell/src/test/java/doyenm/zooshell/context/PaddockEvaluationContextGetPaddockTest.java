package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class PaddockEvaluationContextGetPaddockTest {

    @Test
    public void shouldReturnThePaddockOfTheContext() {
        // Given
        Paddock pad = mock(Paddock.class);
        PaddockEvaluationContext subject = new PaddockEvaluationContext(mock(Zoo.class), pad);
        // When
        Paddock actualPad = subject.getPaddock();
        // Then
        Assertions.assertThat(actualPad).isEqualTo(pad);
    }
}