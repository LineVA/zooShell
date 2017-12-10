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
public class AnimalUpdateContraceptionContextGetAnimalTest {

    @Test
    public void shouldReturnTheAnimalOfTheContext() {
        // Given
        String animal = RandomStringUtils.random(10);
        AnimalUpdateContraceptionContext subject = new AnimalUpdateContraceptionContext(
                mock(Zoo.class),
                animal,
                RandomStringUtils.randomAlphabetic(10));
        // When
        String actual = subject.getAnimal();
        // Then
        Assertions.assertThat(actual).isEqualTo(animal);
    }

}
