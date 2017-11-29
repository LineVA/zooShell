package doyenm.zooshell.context;

import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class KeeperCreationContextGetkeeperTest {

    @Test
    public void shouldReturnTheZooOfTheContext() {
        // Given
        String keeper = RandomStringUtils.randomAlphanumeric(10);
        KeeperCreationContext subject = new KeeperCreationContext(mock(Zoo.class), 
                keeper);
        // When
       String actualKeeper = subject.getKeeper();
        // Then
        Assertions.assertThat(actualKeeper).isEqualTo(keeper);
    }

}
