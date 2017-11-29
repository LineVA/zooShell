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
public class PaddockEvaluationContextSetPaddockTest {

    @Test
    public void shouldSetThePaddockOfTheContext() {
        // Given
        Paddock pad = mock(Paddock.class);
        Paddock pad2 = mock(Paddock.class);
        PaddockEvaluationContext subject = new PaddockEvaluationContext(mock(Zoo.class), pad);
        // When
        subject.setPaddock(pad2);
        Paddock actualPad = subject.getPaddock();
        // Then
        Assertions.assertThat(actualPad).isEqualTo(pad2);
    }
}
