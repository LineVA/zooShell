package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.UpdateFoodQuantity;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class UpdateFoodQuantityCanExecuteTest {

    private final String animalFQ = "animal-fq";
    private final String animalFoodQuantity = "animal-foodQuantity";
    private final String update = "update";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFoodQuantity() {
        // Given
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {this.animalFoodQuantity, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFQ() {
        // Given
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {this.animalFQ, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {this.animalFQ, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        Play play = givenPlay();
        UpdateFoodQuantity updateFoodQuantity = new UpdateFoodQuantity(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFoodQuantity.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
