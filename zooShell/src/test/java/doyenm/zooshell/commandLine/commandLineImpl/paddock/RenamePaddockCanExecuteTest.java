package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.RenamePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.testUtils.TestUtils;
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
        String[] cmd = {this.paddock, this.rename, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {this.pad, this.rename, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.rename, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {this.pad, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.rename, TestUtils.generateString(),};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        RenamePaddock cmdLine = new RenamePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.rename, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
