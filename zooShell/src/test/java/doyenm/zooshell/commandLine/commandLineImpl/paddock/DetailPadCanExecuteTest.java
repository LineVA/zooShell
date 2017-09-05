package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.DetailPad;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DetailPadCanExecuteTest {

    private final String padType = "pad";
    private final String paddockType = "paddock";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {this.paddockType, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {this.padType, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanTwoElements() {
        // Give
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {this.padType, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
