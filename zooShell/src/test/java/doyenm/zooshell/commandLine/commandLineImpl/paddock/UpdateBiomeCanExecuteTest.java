package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class UpdateBiomeCanExecuteTest {

    private final String padBiome = "pad-biome";
    private final String paddockBiome = "paddock-biome";
    private final String update = "update";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {this.paddockBiome, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {this.padBiome, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {this.padBiome, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        Play play = givenPlay();
        UpdateBiome updateBiome = new UpdateBiome(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
