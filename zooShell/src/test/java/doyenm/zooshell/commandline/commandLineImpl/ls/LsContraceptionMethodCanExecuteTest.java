package doyenm.zooshell.commandline.commandLineImpl.ls;

import doyenm.zooshell.commandline.commandimpl.ls.LsContraceptionMethod;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsContraceptionMethodCanExecuteTest {

    private final String CONTRACEPTIONS = "contraceptionMethods";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.CONTRACEPTIONS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {this.CONTRACEPTIONS, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotContraceptionMethods() {
        // Given
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
}
