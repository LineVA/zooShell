package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.RemovePaddock;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RemovePaddockCanExecuteTest {

    private final String pad = "pad";
    private final String paddock = "paddock";
    private final String remove = "remove";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {this.paddock, this.remove, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {this.pad, this.remove, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.remove, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {this.pad, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Give
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.remove};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.remove, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
