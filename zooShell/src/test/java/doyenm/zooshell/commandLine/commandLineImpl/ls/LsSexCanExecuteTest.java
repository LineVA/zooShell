package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsSexCanExecuteTest {

    private final String SEXES = "sexes";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.SEXES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {this.SEXES, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsIncorrect() {
        // Given
        LsSex cmdImpl = new LsSex();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
