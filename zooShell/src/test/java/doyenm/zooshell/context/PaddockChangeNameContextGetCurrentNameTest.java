package doyenm.zooshell.context;

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
public class PaddockChangeNameContextGetCurrentNameTest {

    @Test
    public void shouldReturnTheCurrentNameOfTheContext() {
        // Given
        String currentName = RandomStringUtils.randomAlphabetic(10);
        PaddockChangeNameContext subject = new PaddockChangeNameContext(mock(Zoo.class), currentName, RandomStringUtils.randomAlphabetic(10));
        // When
        String actualCurrentName = subject.getCurrentName();
        // Then
        Assertions.assertThat(actualCurrentName).isEqualTo(currentName);
    }

}
