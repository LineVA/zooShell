package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandImpl.paddock.DetailPad;
import org.apache.commons.lang.RandomStringUtils;
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
        String[] cmd = {this.paddockType, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {this.padType, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanTwoElements() {
        // Give
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        DetailPad cmdLine = new DetailPad(null, null);
        String[] cmd = {this.padType, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
