package doyenm.zooshell.commandline.commandLineImpl;

import doyenm.zooshell.commandline.commandimpl.Load;
import doyenm.zooshell.commandline.commandimpl.Save;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LoadCanExecuteTest {

     private final String LOAD = "load";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Load cmdLine = new Load(null);
        String[] cmd = {this.LOAD, RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotSave() {
        // Given
        Load cmdLine = new Load(null);
        String[] cmd = {RandomStringUtils.random(10), RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsLessThantwoElements() {
        // Given
        Load cmdLine = new Load(null);
        String[] cmd = {this.LOAD};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        Load cmdLine = new Load(null);
        String[] cmd = {this.LOAD, RandomStringUtils.random(10), RandomStringUtils.random(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
