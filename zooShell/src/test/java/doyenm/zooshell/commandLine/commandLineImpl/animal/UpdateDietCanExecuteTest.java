package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.UpdateDiet;
import doyenm.zooshell.testUtils.TestUtils;
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
        String[] cmd = {this.animalDiet, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {this.animalDiet, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateDiet updateDiet = new UpdateDiet(null, null);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
