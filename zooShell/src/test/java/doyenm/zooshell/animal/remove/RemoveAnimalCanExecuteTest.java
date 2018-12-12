package doyenm.zooshell.animal.remove;

import doyenm.zooshell.animal.AnimalValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class RemoveAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String REMOVE = "remove";

    private RemoveAnimal subject;

    @Before
    public void setUp(){
        subject = new RemoveAnimal(mock(AnimalValidator.class), mock(AnimalRemoveController.class));
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        String[] cmd = {this.ANIMAL, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.REMOVE,
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsIncorrect() {
        // Given
        String[] cmd = {this.ANIMAL, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Given
        String[] cmd = {this.ANIMAL, this.REMOVE};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        String[] cmd = {this.ANIMAL, this.REMOVE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
