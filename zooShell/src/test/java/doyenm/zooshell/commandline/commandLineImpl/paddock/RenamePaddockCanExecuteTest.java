package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.RenamePaddock;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RenamePaddockCanExecuteTest {

    private final String pad = "pad";
    private final String paddock = "paddock";
    private final String rename = "rename";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {this.paddock, this.rename, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {this.pad, this.rename, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.rename, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {this.pad, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.rename, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.rename, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
