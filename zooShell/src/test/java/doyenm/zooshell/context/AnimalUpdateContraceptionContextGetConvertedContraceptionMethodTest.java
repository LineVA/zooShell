package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionContextGetConvertedContraceptionMethodTest {

    @Test
    public void shouldReturnTheConvertedContraceptionMethodOfTheContext() {
        // Given
        ContraceptionMethod method = ContraceptionMethod.CASTRACTION;
        AnimalUpdateContraceptionContext subject = new AnimalUpdateContraceptionContext(
                mock(Zoo.class),
                RandomStringUtils.random(10),
                RandomStringUtils.randomAlphabetic(10));
        // When
        subject.setConvertedContraceptionMethod(method);
        ContraceptionMethod actual = subject.getConvertedContraceptionMethod();
        // Then
        Assertions.assertThat(actual).isEqualTo(method);
    }

}
