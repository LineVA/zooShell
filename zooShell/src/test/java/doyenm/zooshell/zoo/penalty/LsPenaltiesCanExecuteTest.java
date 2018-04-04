package doyenm.zooshell.zoo.penalty;

import doyenm.zooshell.zoo.penalty.LsPenalties;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsPenaltiesCanExecuteTest {

    private final String PENALTIES = "penalties";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsPenalties cmdImpl = new LsPenalties();
        String[] cmd = {this.PENALTIES};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsPenalties cmdImpl = new LsPenalties();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsPenalties cmdImpl = new LsPenalties();
        String[] cmd = {this.PENALTIES, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotAnimals() {
        // Given
        LsPenalties cmdImpl = new LsPenalties();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
