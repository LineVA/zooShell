package doyenm.zooshell.paddock.biomes;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author doyenm
 */
public class UpdateBiomeCanExecuteTest {

    private final String padBiome = "pad-biome";
    private final String paddockBiome = "paddock-biome";
    private final String update = "update";

    private UpdateBiome subject;

    @Before
    public void setUp(){
        subject = new UpdateBiome(null, null);
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPaddock() {
        // Given
        String[] cmd = {this.paddockBiome, this.update, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByPad() {
        // Given
        String[] cmd = {this.padBiome, this.update, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        String[] cmd = {this.padBiome, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        String[] cmd = {this.padBiome, this.update, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        String[] cmd = {this.padBiome, this.update, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }
}
