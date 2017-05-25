package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsContraceptionMethodCanExecuteTest {

    private final String LS = "ls";
    private final String CONTRACEPTION = "contraceptionMethod";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Play play = givenPlay();
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(play);
        String[] cmd = {this.LS, this.CONTRACEPTION};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        Play play = givenPlay();
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(play);
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
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(play);
        String[] cmd = {this.LS, this.CONTRACEPTION, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        Play play = givenPlay();
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(play);
        String[] cmd = {TestUtils.generateString(), this.CONTRACEPTION};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotContraceptionMethod() {
        // Given
        Play play = givenPlay();
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(play);
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
