package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.UpdateBiome;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

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
        String[] cmd = {this.paddockBiome, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {this.padBiome, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {this.padBiome, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateBiome updateBiome = new UpdateBiome(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateBiome.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
