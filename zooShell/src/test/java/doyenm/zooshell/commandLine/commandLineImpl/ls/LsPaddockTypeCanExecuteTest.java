package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.launch.play.Play;
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

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddockType() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {this.LS, this.PADDOCK_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPadType() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {this.LS, this.PAD_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {this.LS, this.PADDOCK_TYPE, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {TestUtils.generateString(), this.PADDOCK_TYPE};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotPaddockTypeNorPadType() {
        // Given
        Play play = givenPlay();
        LsPaddockType cmdImpl = new LsPaddockType(play);
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
