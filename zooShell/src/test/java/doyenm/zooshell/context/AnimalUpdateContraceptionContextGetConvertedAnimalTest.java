package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionContextGetConvertedAnimalTest {

    @Test
    public void shouldReturnTheConvertedAnimalOfTheContext() {
        // Given
        Animal animal = mock(Animal.class);
        AnimalUpdateContraceptionContext subject = new AnimalUpdateContraceptionContext(
                mock(Zoo.class),
                RandomStringUtils.random(10),
                RandomStringUtils.randomAlphabetic(10));
        // When
        subject.setConvertedAnimal(animal);
        Animal actual = subject.getConvertedAnimal();
        // Then
        Assertions.assertThat(actual).isEqualTo(animal);
    }

}
