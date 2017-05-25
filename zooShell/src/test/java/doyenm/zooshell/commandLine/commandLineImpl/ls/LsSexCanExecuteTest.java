package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsSexCanExecuteTest {

    private final String LS = "ls";
    private final String SEX = "sex";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Play play = givenPlay();
        LsSex cmdImpl = new LsSex(play);
        String[] cmd = {this.LS, this.SEX};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        Play play = givenPlay();
        LsSex cmdImpl = new LsSex(play);
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
        LsSex cmdImpl = new LsSex(play);
        String[] cmd = {this.LS, this.SEX, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        Play play = givenPlay();
        LsSex cmdImpl = new LsSex(play);
        String[] cmd = {TestUtils.generateString(), this.SEX};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotSex() {
        // Given
        Play play = givenPlay();
        LsSex cmdImpl = new LsSex(play);
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
