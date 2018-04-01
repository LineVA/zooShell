package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.animal.diets.UpdateDiet;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UpdateDietCanExecuteTest {

    private final String animalDiet = "animal-diet";
    private final String update = "update";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {this.animalDiet, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {this.animalDiet, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
