package doyenm.zooshell.animal.rename;

import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class RenameAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String RENAME = "rename";

    private RenameAnimal subject;

    @Before
    public void setUp(){
        subject = new RenameAnimal(mock(AnimalChangeNameValidator.class), mock(AnimalChangeNameController.class));
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        String[] cmd = {this.ANIMAL, this.RENAME, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotAnimal() {
        // Given
        String falseAnimal = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {falseAnimal, this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(ANIMAL);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        String[] cmd = {this.ANIMAL, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        String[] cmd = {this.ANIMAL, this.RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        String[] cmd = {this.ANIMAL, this.RENAME,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
