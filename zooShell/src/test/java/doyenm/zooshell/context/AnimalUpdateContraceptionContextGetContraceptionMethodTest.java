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
public class AnimalUpdateContraceptionContextGetContraceptionMethodTest {

    @Test
    public void shouldReturnTheContraceptionMethodOfTheContext() {
        // Given
        String method = RandomStringUtils.random(10);
        AnimalUpdateContraceptionContext subject = new AnimalUpdateContraceptionContext(
                mock(Zoo.class), 
                RandomStringUtils.random(10),
                method);
        // When
        String actual = subject.getContraceptionMethod();
        // Then
        Assertions.assertThat(actual).isEqualTo(method);
    }

}
