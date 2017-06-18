package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.UpdatePaddockType;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeCanExecuteTest {

    private final String padType = "pad-type";
    private final String paddockType = "paddock-type";
    private final String update = "update";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.paddockType, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
