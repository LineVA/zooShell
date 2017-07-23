package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.RemovePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.testUtils.TestUtils;
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
        String[] cmd = {this.paddock, this.remove, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {this.pad, this.remove, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.remove, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {this.pad, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Give
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.remove};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        RemovePaddock cmdLine = new RemovePaddock(null, null);
        String[] cmd = {TestUtils.generateString(), this.remove, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
