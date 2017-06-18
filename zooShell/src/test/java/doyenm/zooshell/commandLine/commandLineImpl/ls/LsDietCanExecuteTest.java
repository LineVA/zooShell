package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsDiet;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsDietCanExecuteTest {

    private final String LS = "ls";
    private final String DIET = "diet";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.LS, this.DIET};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.LS, this.DIET, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {TestUtils.generateString(), this.DIET};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotDiet() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
