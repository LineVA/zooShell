package doyenm.zooshell.commandLine.commandLineImpl;

import doyenm.zooshell.commandLine.commandImpl.Save;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class SaveCanExecuteTest {

    private final String SAVE = "save";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Save cmdLine = new Save(null);
        String[] cmd = {this.SAVE, RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotSave() {
        // Given
        Save cmdLine = new Save(null);
        String[] cmd = {RandomStringUtils.random(10), RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsLessThantwoElements() {
        // Given
        Save cmdLine = new Save(null);
        String[] cmd = {this.SAVE};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        Save cmdLine = new Save(null);
        String[] cmd = {this.SAVE, RandomStringUtils.random(10), RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
