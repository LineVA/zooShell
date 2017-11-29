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
public class PaddockChangeNameContextGetNewNameTest {

    @Test
    public void shouldReturnTheNewNameOfTheContext() {
        // Given
        String newName = RandomStringUtils.randomAlphabetic(10);
        PaddockChangeNameContext subject = new PaddockChangeNameContext(mock(Zoo.class), RandomStringUtils.randomAlphabetic(10), newName);
        // When
        String actualNewName = subject.getNewName();
        // Then
        Assertions.assertThat(actualNewName).isEqualTo(newName);
    }

}
