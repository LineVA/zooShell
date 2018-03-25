package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.commandline.commandimpl.zoo.RenameZoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RenameZooCanExecuteTest {

    private final static String ZOO = "zoo";
    private final static String RENAME = "rename";

    @Test
    public void shouldReturnTrueWhenTheElementsAreCorrect() {
        // Given
        RenameZoo renameZoo = new RenameZoo(null);
        String[] cmd = {ZOO, RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = renameZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessthanThreeElements() {
        // Given
        RenameZoo renameZoo = new RenameZoo(null);
        String[] cmd = {ZOO, RENAME};
        // When
        boolean actualResult = renameZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        RenameZoo renameZoo = new RenameZoo(null);
        String[] cmd = {ZOO, RENAME,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = renameZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RenameZoo renameZoo = new RenameZoo(null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = renameZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RenameZoo renameZoo = new RenameZoo(null);
        String[] cmd = {ZOO, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = renameZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
