package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsBiome;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsBiomeCanExecuteTest {

    private final String LS = "ls";
    private final String BIOME = "biome";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.LS, this.BIOME};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.LS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.LS, this.BIOME, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotLs() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {TestUtils.generateString(), this.BIOME};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementOfTheCommandIsNotBiome() {
        // Given
        LsBiome cmdImpl = new LsBiome();
        String[] cmd = {this.LS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
