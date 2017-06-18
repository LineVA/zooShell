package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsContraceptionMethodCanExecuteTest {

    private final String LS = "ls";
    private final String CONTRACEPTION = "contraceptionMethod";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.LS, this.CONTRACEPTION};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.LS, this.CONTRACEPTION, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {TestUtils.generateString(), this.CONTRACEPTION};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotContraceptionMethod() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
