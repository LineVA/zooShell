package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.commandline.commandimpl.animal.RemoveAnimal;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RemoveAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String REMOVE = "remove";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        RemoveAnimal commandImpl = new RemoveAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        RemoveAnimal commandImpl = new RemoveAnimal(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.REMOVE,
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsIncorrect() {
        // Given
        RemoveAnimal commandImpl = new RemoveAnimal(null, null);
        String[] cmd = {this.ANIMAL, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Given
        RemoveAnimal commandImpl = new RemoveAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.REMOVE};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        RemoveAnimal commandImpl = new RemoveAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.REMOVE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
