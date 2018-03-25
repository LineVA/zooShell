package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.commandline.commandimpl.animal.LsAnimal;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsAnimalCanExecuteTest {

    private final String ANIMALS = "animals";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsAnimal cmdImpl = new LsAnimal();
        String[] cmd = {this.ANIMALS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsAnimal cmdImpl = new LsAnimal();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsAnimal cmdImpl = new LsAnimal();
        String[] cmd = {this.ANIMALS, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotAnimals() {
        // Given
        LsAnimal cmdImpl = new LsAnimal();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
