package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.paddock.list.LsPaddock;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsPaddockCanExecuteTest {

    private final String pads = "pads";
    private final String paddocks = "paddocks";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndContainsPaddocks() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.paddocks};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndContainsPads() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.pads};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheElementIsIncorrect() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanOneElement() {
        // Give
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElement() {
        // Given
        LsPaddock cmdLine = new LsPaddock();
        String[] cmd = {this.pads, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
