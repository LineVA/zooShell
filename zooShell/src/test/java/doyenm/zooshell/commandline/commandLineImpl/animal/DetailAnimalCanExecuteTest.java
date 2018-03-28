package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.animal.details.DetailAnimal;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DetailAnimalCanExecuteTest {

    private final String animal = "animal";
    private final String detailed = "detailed";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndContainsOnlyTwoElements() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {this.animal, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndContainsThreeElements() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {this.animal, RandomStringUtils.randomAlphabetic(10), this.detailed};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrectAndThereIsOnlyTwoElements() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrectAndThereIsThreeElements() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), this.detailed};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanTwoElements() {
        // Give
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheThirdElementIsIncorrect() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {this.animal, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        DetailAnimal cmdLine = new DetailAnimal(null, null);
        String[] cmd = {this.animal, RandomStringUtils.randomAlphabetic(10), this.detailed, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
