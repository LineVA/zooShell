package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsPaddockTypeCanExecuteTest {

    private final String LS = "ls";
    private final String PADDOCK_TYPE = "paddockType";
    private final String PAD_TYPE = "padType";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddockType() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.LS, this.PADDOCK_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPadType() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.LS, this.PAD_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.LS, this.PADDOCK_TYPE, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {TestUtils.generateString(), this.PADDOCK_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotPaddockTypeNorPadType() {
        // Given
        LsPaddockType cmdImpl = new LsPaddockType();
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
