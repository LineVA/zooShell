package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.rename.PaddockChangeNameContext;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameContextGetZooTest {

    @Test
    public void shouldReturnTheZooOfTheContext() {
        // Given
        Zoo zoo = mock(Zoo.class);
        PaddockChangeNameContext subject = new PaddockChangeNameContext(zoo,
                RandomStringUtils.random(10), RandomStringUtils.randomAlphabetic(10));
        // When
        Zoo actualZoo = subject.getZoo();
        // Then
        Assertions.assertThat(actualZoo).isEqualTo(zoo);
    }

}