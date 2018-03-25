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
public class KeeperCreationContextGetZooTest {

    @Test
    public void shouldReturnTheZooOfTheContext() {
        // Given
        Zoo zoo = mock(Zoo.class);
        KeeperCreationContext subject = new KeeperCreationContext(zoo, 
                RandomStringUtils.random(10));
        // When
        Zoo actualZoo = subject.getZoo();
        // Then
        Assertions.assertThat(actualZoo).isEqualTo(zoo);
    }

}
