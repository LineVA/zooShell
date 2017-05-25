package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdatePaddockType;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeCanExecuteTest {

    private final String padType = "pad-type";
    private final String paddockType = "paddock-type";
    private final String update = "update";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {this.paddockType, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {this.padType, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        Play play = givenPlay();
        UpdatePaddockType updatePaddockType = new UpdatePaddockType(play);
        String[] cmd = {this.padType, this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updatePaddockType.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
