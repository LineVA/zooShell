package doyenm.zooshell.commandline.commandLineImpl;

import doyenm.zooshell.commandline.commandImpl.LsSpecie;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsSpecieCanExecuteTest {

    private final String SPECIES = "species";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsSpecie cmdImpl = new LsSpecie();
        String[] cmd = {this.SPECIES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsSpecie cmdImpl = new LsSpecie();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsSpecie cmdImpl = new LsSpecie();
        String[] cmd = {this.SPECIES, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotSpecies() {
        // Given
        LsSpecie cmdImpl = new LsSpecie();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
