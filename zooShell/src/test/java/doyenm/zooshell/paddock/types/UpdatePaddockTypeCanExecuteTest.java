package doyenm.zooshell.paddock.types;

import doyenm.zooshell.paddock.types.UpdatePaddockType;
import org.apache.commons.lang.RandomStringUtils;
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
        String[] cmd = {this.paddockType, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(null, null);
        String[] cmd = {this.padType, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
