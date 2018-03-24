package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.commandline.commandimpl.animal.UpdateFastDays;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UpdateFastDaysCanExecuteTest {

    private final String animalFD = "animal-fd";
    private final String animalFastDays = "animal-fastDays";
    private final String update = "update";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFastDays() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {this.animalFastDays, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFD() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {this.animalFD, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {this.animalFD, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
