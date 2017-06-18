package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsSexCanExecuteTest {

    private final String LS = "ls";
    private final String SEX = "sex";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.LS, this.SEX};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.LS, this.SEX, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {TestUtils.generateString(), this.SEX};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotSex() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
