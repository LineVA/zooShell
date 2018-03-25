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
public class KeeperEvaluationContextGetKeeperTest {

    @Test
    public void shouldReturnTheKeeperOfTheContext() {
        // Given
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class), 
                keeper);
        // When
        AnimalKeeper actualKeeper = subject.getKeeper();
        // Then
        Assertions.assertThat(actualKeeper).isEqualTo(keeper);
    }

}
