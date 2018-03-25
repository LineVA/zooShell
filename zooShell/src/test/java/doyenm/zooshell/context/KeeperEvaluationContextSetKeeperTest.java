package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextSetKeeperTest {

    @Test
    public void shouldSetTheKeeperOfTheContext() {
        // Given
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class), 
                null);
        // When
        subject.setKeeper(keeper);
        AnimalKeeper actualKeeper = subject.getKeeper();
        // Then
        Assertions.assertThat(actualKeeper).isEqualTo(keeper);
    }

}
