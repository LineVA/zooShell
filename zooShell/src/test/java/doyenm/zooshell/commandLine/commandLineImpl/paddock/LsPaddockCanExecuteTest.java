package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.LsPaddock;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsPaddockCanExecuteTest {

    private final String pad = "pad";
    private final String paddock = "paddock";
    private final String ls = "ls";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndFinishesByPaddock() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.ls, this.paddock};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndFinishesByPad() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.ls, this.pad};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {TestUtils.generateString(), this.pad};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.ls, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanTwoElements() {
        // Give
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.ls, this.pad, TestUtils.generateString()};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
