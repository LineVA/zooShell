package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsPaddockTypeCanExecuteTest {

    private final String PADDOCK_TYPES = "paddockTypes";
    private final String PAD_TYPES = "padTypes";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddockType() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.PADDOCK_TYPES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPadType() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.PAD_TYPES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.PADDOCK_TYPES, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsIncorrect() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
