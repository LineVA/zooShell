package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.UpdateFastDays;
import doyenm.zooshell.testUtils.TestUtils;
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
        String[] cmd = {this.animalFastDays, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFD() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {this.animalFD, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {this.animalFD, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateFastDays updateFastDays = new UpdateFastDays(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
