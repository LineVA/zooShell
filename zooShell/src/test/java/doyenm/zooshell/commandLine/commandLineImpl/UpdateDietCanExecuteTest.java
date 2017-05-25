package doyenm.zooshell.commandLine.commandLineImpl;

import doyenm.zooshell.commandLine.commandImpl.UpdateDiet;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class UpdateDietCanExecuteTest {

    private final String animalDiet = "animal-diet";
    private final String update = "update";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Play play = givenPlay();
        UpdateDiet updateDiet = new UpdateDiet(play);
        String[] cmd = {this.animalDiet, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateDiet updateDiet = new UpdateDiet(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateDiet updateDiet = new UpdateDiet(play);
        String[] cmd = {this.animalDiet, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        Play play = givenPlay();
        UpdateDiet updateDiet = new UpdateDiet(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        Play play = givenPlay();
        UpdateDiet updateDiet = new UpdateDiet(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateDiet.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
