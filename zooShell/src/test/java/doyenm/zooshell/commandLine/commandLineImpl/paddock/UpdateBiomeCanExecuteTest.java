package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
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

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {this.paddockBiome, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {this.padBiome, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {this.padBiome, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
