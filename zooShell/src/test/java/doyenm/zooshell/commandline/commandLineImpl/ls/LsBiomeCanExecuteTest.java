package doyenm.zooshell.commandline.commandLineImpl.ls;

import doyenm.zooshell.commandline.commandimpl.paddock.LsBiome;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsBiomeCanExecuteTest {

    private static final String BIOMES = "Biomes";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.BIOMES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.BIOMES, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotBiomes() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
}
