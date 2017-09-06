package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.UpdateFoodQuantity;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UpdateFoodQuantityCanExecuteTest {

    private final String animalFQ = "animal-fq";
    private final String animalFoodQuantity = "animal-foodQuantity";
    private final String update = "update";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFoodQuantity() {
        // Given
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {this.animalFoodQuantity, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFQ() {
        // Given
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {this.animalFQ, this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {this.animalFQ, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.update, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
