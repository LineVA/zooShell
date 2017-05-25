package doyenm.zooshell.commandLine.commandLineImpl;

import doyenm.zooshell.commandLine.commandImpl.UpdateFastDays;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class UpdateFastDaysCanExecuteTest {

    private final String animalFD = "animal-fd";
    private final String animalFastDays = "animal-fastDays";
    private final String update = "update";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFastDays() {
        // Given
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {this.animalFastDays, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalFD() {
        // Given
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {this.animalFD, this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {this.animalFD, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(),};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        Play play = givenPlay();
        UpdateFastDays updateFastDays = new UpdateFastDays(play);
        String[] cmd = {TestUtils.generateString(), this.update, TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = updateFastDays.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
