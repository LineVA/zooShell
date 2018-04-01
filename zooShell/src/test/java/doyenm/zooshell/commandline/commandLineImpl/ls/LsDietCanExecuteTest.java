package doyenm.zooshell.commandline.commandLineImpl.ls;

import doyenm.zooshell.animal.diets.LsDiet;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsDietCanExecuteTest {

    private final String DIETS = "diets";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.DIETS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {this.DIETS, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotDiets() {
        // Given
        LsDiet cmdImpl = new LsDiet();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}