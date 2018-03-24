package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.commandline.commandImpl.animal.CreateAnimal;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreateAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String CREATE = "create";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotAnimal() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String falseAnimal = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {falseAnimal, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(ANIMAL);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String falseCreate = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.ANIMAL, falseCreate, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanSixElements() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSixElements() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
