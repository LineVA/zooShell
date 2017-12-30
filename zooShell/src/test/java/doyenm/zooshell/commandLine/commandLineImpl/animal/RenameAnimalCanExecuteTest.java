package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.RenameAnimal;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RenameAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String RENAME = "rename";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        RenameAnimal commandImpl = new RenameAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.RENAME, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotAnimal() {
        // Given
        RenameAnimal commandImpl = new RenameAnimal(null, null);
        String falseAnimal = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {falseAnimal, this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(ANIMAL);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        RenameAnimal commandImpl = new RenameAnimal(null, null);
        String[] cmd = {this.ANIMAL, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        RenameAnimal commandImpl = new RenameAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        RenameAnimal commandImpl = new RenameAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.RENAME, 
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
